package googleplay.itheima.cn.googleplay.googleplay.itheima.cn.googleplay.tools;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/**
 * Created by yin on 2016/11/22.
 */
public class ViewUtils {
    public static void removeParent(View view) {
        //先找到父类，通过父类移除子类
        ViewParent parent = view.getParent();
        //一般所有控件的父类是ViewGroup
        if (parent instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) parent;
            group.removeView(view);
        }
    }
}
