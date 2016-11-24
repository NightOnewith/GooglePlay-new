package googleplay.itheima.cn.googleplay.googleplay.itheima.cn.googleplay.Fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import googleplay.itheima.cn.googleplay.R;
import googleplay.itheima.cn.googleplay.googleplay.itheima.cn.googleplay.tools.ViewUtils;

/**
 * Created by yin on 2016/11/21.
 */
public class HomeFragment extends BaseFragment {

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    /* 创建成功的界面 */
    public View createSuccessView() {
        TextView textView = new TextView(getActivity());
        textView.setText("加载成功了...");
        textView.setTextSize(30);
        return textView;
    }

    public LoadResult load() {
        return LoadResult.success;
    }
}
