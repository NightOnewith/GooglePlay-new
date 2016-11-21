package googleplay.itheima.cn.googleplay.googleplay.itheima.cn.googleplay.Fragment;

import android.support.v4.app.Fragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yin on 2016/11/21.
 */
public class FragmentFactory {
    private static Map<Integer, Fragment> mFragmets = new HashMap<Integer, Fragment>();

    public static Fragment createFragment(int position){
        Fragment fragment = null;
        fragment = mFragmets.get(position); //从集合中取出来Fragment
        if(fragment == null){ //如果集合中没有取出来，需要重新创建
            if (position == 0) {
                return new HomeFragment();
            } else if(position == 1){
                return new AppFragment();
            } else if(position == 2){
                return new GameFragment();
            } else if(position == 3){
                return new SubjectFragment();
            } else if(position == 4){
                return new CategoryFragment();
            } else if(position == 5){
                return new TopFragment();
            }
            if(mFragmets != null){
                mFragmets.put(position, fragment); //把创建好的Fragment放到集合中缓存起来
            }
        }
        return fragment;
    }
}
