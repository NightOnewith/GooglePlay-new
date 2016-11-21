package googleplay.itheima.cn.googleplay.googleplay.itheima.cn.googleplay.tools;

import android.content.res.Resources;
import googleplay.itheima.cn.googleplay.BaseApplication;

/**
 * Created by yin on 2016/11/21.
 */
public class UiUtils {
    public static String[] getStringArray(int tabNames){
        return getResource().getStringArray(tabNames);
    }

    public static Resources getResource(){
        return BaseApplication.getApplication().getResources();
    }

    /** dip转换px */
    public static int dip2px(int dip) {
        final float scale = getResource().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /** pxz转换dip */

    public static int px2dip(int px) {
        final float scale = getResource().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }
}
