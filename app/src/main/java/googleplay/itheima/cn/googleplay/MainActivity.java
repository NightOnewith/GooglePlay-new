package googleplay.itheima.cn.googleplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import googleplay.itheima.cn.googleplay.googleplay.itheima.cn.googleplay.Fragment.AppFragment;
import googleplay.itheima.cn.googleplay.googleplay.itheima.cn.googleplay.Fragment.HomeFragment;

public class MainActivity extends AppCompatActivity implements OnQueryTextListener {

    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mdrawerLayout = (DrawerLayout) findViewById(R.id.dl);
        mViewPager = (ViewPager) findViewById(R.id.vp);
        mViewPager.setAdapter(new MainAdapter(getSupportFragmentManager()));

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer_am);


        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.Tab tab1 = actionBar.newTab().setText("标签一").setTabListener(new MyTabListener());
        actionBar.addTab(tab1);
        ActionBar.Tab tab2 = actionBar.newTab().setText("标签二").setTabListener(new MyTabListener());
        actionBar.addTab(tab2);
        ActionBar.Tab tab3 = actionBar.newTab().setText("标签三").setTabListener(new MyTabListener());
        actionBar.addTab(tab3);
        ActionBar.Tab tab4 = actionBar.newTab().setText("标签四").setTabListener(new MyTabListener());
        actionBar.addTab(tab4);

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
        //当页面发生变化时调用(切换页面时tab标签也切换到相应位置)
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                getSupportActionBar().setSelectedNavigationItem(position);
            }
        });
    }

    private class MyTabListener implements ActionBar.TabListener {
        //当tab标签被选中时Viewpage切换到指定位置
        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            mViewPager.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }
    }

    private class MainAdapter extends FragmentStatePagerAdapter {

        public MainAdapter(FragmentManager fm) {
            super(fm);
        }
        //每个条目返回的Fragment
        @Override
        public Fragment getItem(int position) {
            if(position==0){
                return new HomeFragment();
            } else {
                return new AppFragment();
            }
        }
        //一共有几个条目
        @Override
        public int getCount() {
            return 4;
        }
    }

    public void onclick(View v) {
        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
        startActivity(intent);
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
