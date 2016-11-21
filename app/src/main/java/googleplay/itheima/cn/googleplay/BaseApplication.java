package googleplay.itheima.cn.googleplay;

import android.app.Application;
import android.content.Context;

/**
 * Created by yin on 2016/11/21.
 */

/**
 * 代表当前应用程序
 */
public class BaseApplication extends Application {
    private static BaseApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static Context getApplication(){
        return application;
    }
}
