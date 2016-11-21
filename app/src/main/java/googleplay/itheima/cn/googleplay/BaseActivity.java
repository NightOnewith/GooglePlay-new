package googleplay.itheima.cn.googleplay;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by yin on 2016/11/21.
 */
public class BaseActivity extends ActionBarActivity {

    //管理运行的所有App
    List<BaseActivity> mActivities = new LinkedList<BaseActivity>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //同步
        synchronized (mActivities){
            mActivities.add(this);
        }


        init();
        initView();
        initActionBar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        synchronized (mActivities){
            mActivities.remove(this);
        }

    }

    public void killAll(){
        List<BaseActivity> copy;
        //复制一份mActivities集合
        synchronized (mActivities) {
            copy = new LinkedList<BaseActivity>(mActivities);
        }
        for (BaseActivity activity : copy){
            activity.finish();
        }
        //杀死当前进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    protected void init(){

    }

    protected void initView(){

    }

    protected void initActionBar(){

    }
}
