package googleplay.itheima.cn.googleplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnQueryTextListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.Tab tab1 = actionBar.newTab().setText("标签一").setTabListener(new MyTabListener());
        actionBar.addTab(tab1);
        ActionBar.Tab tab2 = actionBar.newTab().setText("标签二").setTabListener(new MyTabListener());
        actionBar.addTab(tab2);
        ActionBar.Tab tab3 = actionBar.newTab().setText("标签三").setTabListener(new MyTabListener());
        actionBar.addTab(tab3);
        ActionBar.Tab tab4 = actionBar.newTab().setText("标签四").setTabListener(new MyTabListener());
        actionBar.addTab(tab4);
    }

    private class MyTabListener implements ActionBar.TabListener{

        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }
    }
    public void onclick(View v){
        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions,menu);

//        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
//        searchView.setOnQueryTextListener(this); //搜索的监听
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_search :
                Toast.makeText(getApplicationContext(),"搜索",Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
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
        return true ;
    }
}
