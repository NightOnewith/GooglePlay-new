package googleplay.itheima.cn.googleplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import googleplay.itheima.cn.googleplay.googleplay.itheima.cn.googleplay.Fragment.AppFragment;
import googleplay.itheima.cn.googleplay.googleplay.itheima.cn.googleplay.Fragment.CategoryFragment;
import googleplay.itheima.cn.googleplay.googleplay.itheima.cn.googleplay.Fragment.FragmentFactory;
import googleplay.itheima.cn.googleplay.googleplay.itheima.cn.googleplay.Fragment.GameFragment;
import googleplay.itheima.cn.googleplay.googleplay.itheima.cn.googleplay.Fragment.HomeFragment;
import googleplay.itheima.cn.googleplay.googleplay.itheima.cn.googleplay.Fragment.SubjectFragment;
import googleplay.itheima.cn.googleplay.googleplay.itheima.cn.googleplay.Fragment.TopFragment;
import googleplay.itheima.cn.googleplay.googleplay.itheima.cn.googleplay.tools.UiUtils;

public class MainActivity extends BaseActivity implements OnQueryTextListener {

    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ViewPager mViewPager;
    private PagerTabStrip pager_tab_strip;
    private String[] tab_names; //标签的名称

    @Override
    protected void init() {
        tab_names = UiUtils.getStringArray(R.array.tab_names);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);

        mdrawerLayout = (DrawerLayout) findViewById(R.id.dl);

        mViewPager = (ViewPager) findViewById(R.id.vp);
        mViewPager.setAdapter(new MainAdapter(getSupportFragmentManager()));

        pager_tab_strip = (PagerTabStrip) findViewById(R.id.pager_tab_strip);
        //设置标签下划线颜色
        pager_tab_strip.setTabIndicatorColor(getResources().getColor(R.color.indicatorcolor));
    }

    @Override
    protected void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer_am);

        //参数1：当前actionbar所在activity，参数2：控制哪个抽屉，参数3;按钮的图片，参数4：抽屉打开的描述，参数5：抽屉关闭的描述
        drawerToggle = new ActionBarDrawerToggle(this, mdrawerLayout, R.drawable.ic_drawer_am, R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Toast.makeText(getApplicationContext(), "抽屉关闭了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Toast.makeText(getApplicationContext(), "抽屉打开了", Toast.LENGTH_SHORT).show();
            }
        };
        //让开关和actionbar建立关系
        mdrawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    private class MainAdapter extends FragmentStatePagerAdapter {

        public MainAdapter(FragmentManager fm) {
            super(fm);
        }

        //每个条目返回的Fragment
        @Override
        public Fragment getItem(int position) {
            //通过工厂生产Fragment
            return FragmentFactory.createFragment(position);
        }

        //一共有几个条目
        @Override
        public int getCount() {

            return tab_names.length;
        }
        //获取标签名
        @Override
        public CharSequence getPageTitle(int position) {

            return tab_names[position];
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);

//        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
//        searchView.setOnQueryTextListener(this); //搜索的监听
        return super.onCreateOptionsMenu(menu);
    }

    //处理actionbar菜单条目点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Toast.makeText(getApplicationContext(), "搜索", Toast.LENGTH_SHORT).show();
                return true;
        }
        return drawerToggle.onOptionsItemSelected(item) | super.onOptionsItemSelected(item);
    }

    //当搜索提交时
    @Override
    public boolean onQueryTextSubmit(String query) {
//        Toast.makeText(getApplicationContext(),query,Toast.LENGTH_SHORT).show();
        return true;
    }

    //当搜索发生时
    @Override
    public boolean onQueryTextChange(String newText) {
//        Toast.makeText(getApplicationContext(),"搜索：" + newText,Toast.LENGTH_SHORT).show();
        return true;
    }
}
