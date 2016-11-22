package googleplay.itheima.cn.googleplay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Filter;

/**
 * Created by yin on 2016/11/21.
 */
public class BaseActivity extends ActionBarActivity {

    //管理运行的所有app
    public final static List<BaseActivity> mActivities = new LinkedList<BaseActivity>();

//    注册广播接收器
//    private KillReceiver receiver;
//    private class KillReceiver extends BroadcastReceiver{
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            finish();
//        }
//    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        receiver = new KillReceiver();
//        IntentFilter filter = new IntentFilter("com.itheima.google.killall");
//        registerReceiver(receiver, filter);

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
//        //注销广播监听
//        if (receiver != null){
//            unregisterReceiver(receiver);
//            receiver = null;
//        }

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
