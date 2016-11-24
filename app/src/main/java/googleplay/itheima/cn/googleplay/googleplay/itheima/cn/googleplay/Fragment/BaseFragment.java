package googleplay.itheima.cn.googleplay.googleplay.itheima.cn.googleplay.Fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import googleplay.itheima.cn.googleplay.R;
import googleplay.itheima.cn.googleplay.googleplay.itheima.cn.googleplay.tools.ViewUtils;

/**
 * Created by yin on 2016/11/24.
 */
public abstract class BaseFragment extends Fragment {
    public static final int STATE_UNKNOWN = 0;
    public static final int STATE_LOADING = 1;
    public static final int STATE_ERROR = 2;
    public static final int STATE_EMPTY = 3;
    public static final int STATE_SUCCESS = 4;
    public static int state = STATE_UNKNOWN;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (frameLayout == null) {
            frameLayout = new FrameLayout(getActivity()); //之前的frameLayout已经记录了一个父类viewPager
            init();//在frameLayout中显示4种不同的界面
        } else {
            ViewUtils.removeParent(frameLayout); //移除之前的父类
        }

        show();//根据服务器的状态切换界面

        return frameLayout; //拿到当前的viewPager，添加这个frameLayout
    }

    private FrameLayout frameLayout;
    private View loadingView; //加载中的届满
    private View errorView; //错误界面
    private View emptyView; //空的界面
    private View successView; //成功界面

    /**
     * 在frameLayout中显示4种不同的界面
     */
    private void init() {
        loadingView = createLoadingView(); //创建加载中界面
        if (loadingView != null) {
            frameLayout.addView(loadingView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }

        errorView = createErrorView(); //创建错误界面
        if (errorView != null) {
            frameLayout.addView(errorView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }

        emptyView = createEmptyView(); //创建空的界面
        if (emptyView != null) {
            frameLayout.addView(emptyView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        showPage();//根据不同的状态下切换不同的界面
    }

    /* 创建加载中的界面 */
    private View createLoadingView() {
        View view = View.inflate(getActivity(), R.layout.loadpage_loading, null);
        return view;
    }

    /* 创建错误界面 */
    private View createErrorView() {
        View view = View.inflate(getActivity(), R.layout.loadpage_error, null);
        Button page_bt = (Button) view.findViewById(R.id.page_bt);
        page_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show();
            }
        });
        return view;
    }

    /* 创建空的界面 */
    private View createEmptyView() {
        View view = View.inflate(getActivity(), R.layout.loadpage_empty, null);
        return view;
    }

    /**
     * 根据不同的状态下切换不同的界面
     */
    private void showPage() {
        if (loadingView != null) {
            loadingView.setVisibility(state == STATE_UNKNOWN || state == STATE_LOADING ? View.VISIBLE : View.INVISIBLE);
        }
        if (errorView != null) {
            errorView.setVisibility(state == STATE_ERROR ? View.VISIBLE : View.INVISIBLE);
        }
        if (emptyView != null) {
            emptyView.setVisibility(state == STATE_EMPTY ? View.VISIBLE : View.INVISIBLE);
        }
        if (successView != null) {
            successView.setVisibility(View.INVISIBLE);
        }
        if (state == STATE_SUCCESS) {
            successView = createSuccessView();
            if (successView != null) {
                frameLayout.addView(successView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }
        } else {
            if(successView != null){
                successView.setVisibility(successView.INVISIBLE);
            }
        }
    }

    /**
     * 创建成功的界面
     * @return
     */
    public abstract View createSuccessView();

    /**
     * 请求服务器
     * @return
     */
    protected abstract LoadResult load();

    /**
     * 根据服务器的状态切换界面
     */
    public void show() {
        if (state == STATE_ERROR || state == STATE_EMPTY || state == STATE_SUCCESS) {
            state = STATE_LOADING;
        }
        //请求服务器，获取服务器上的数据，进行判断
        //请求服务器，返回一个结果
        new Thread() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                final LoadResult result = load();
                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (result != null) {
                                state = result.getValue();
                                showPage(); //状态改变了，重新判断当前应该显示哪个界面
                            }
                        }
                    });
                }
            }
        }.start();
        showPage();
    }

    public enum LoadResult {
        error(2), empty(3), success(4);
        int value;

        LoadResult(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
