package googleplay.itheima.cn.googleplay;

import android.support.v7.app.ActionBar;

/**
 * Created by yin on 2016/11/10.
 */
public class DetailActivity extends BaseActivity {

    @Override
    protected void initView() {
        setContentView(R.layout.activity_detail);
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDefaultDisplayHomeAsUpEnabled(true);
    }
}
