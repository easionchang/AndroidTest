/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-3-2 下午2:41:38
 */

package cn.flyrise.android3.test.actionbar;

import cn.flyrise.android3.test.R;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 类功能描述：</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * 修改时间：</br>
 * 修改备注：</br>
 */
public class ActionBarTabActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //androidVersion 11以上默认titleBar就是ActionBar
        //getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

        setContentView(R.layout.actionbar_tab);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem item = menu.add(0, 1, 0, "ADD");
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        item.setIcon(android.R.drawable.ic_menu_add);
        
        MenuItem item2 = menu.add(0, 2, 0, "Remove");
        item2.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        item2.setIcon(android.R.drawable.ic_menu_delete);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 1) {
            addTab();
        }else if(item.getItemId() == 2){
            deleteTab();
        }

        return true;
    }

    private void addTab() {
        ActionBar actionbar = getActionBar();
        
        //NAVIGATION_MODE_TABS设置了这行才能将actionbar设置为tab的样式
        if(actionbar.getNavigationMode() != ActionBar.NAVIGATION_MODE_TABS){
            actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        }
        
        actionbar.addTab(actionbar.newTab().setText("Tab"+actionbar.getNavigationItemCount())
                .setTabListener(new MyActionTabListener(new MyFragment())));
    }
    
    private void deleteTab(){
        ActionBar actionbar = getActionBar();
        actionbar.removeTabAt(actionbar.getNavigationItemCount() - 1);
    }

    class MyActionTabListener implements TabListener {
        private Fragment fragment;

        public MyActionTabListener(Fragment fragment) {
            this.fragment = fragment;
        }

        @Override
        public void onTabUnselected(Tab tab, FragmentTransaction ft) {
            ft.remove(fragment);
            Toast.makeText(ActionBarTabActivity.this, "onTabUnselected---",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onTabSelected(Tab tab, FragmentTransaction ft) {
            ft.add(R.id.tab_content, fragment,"TAB1");
            Toast.makeText(ActionBarTabActivity.this, "onTabUnselected+++",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onTabReselected(Tab tab, FragmentTransaction ft) {
            Toast.makeText(ActionBarTabActivity.this, "onTabReselected***",
                    Toast.LENGTH_SHORT).show();
        }
    }

    class MyFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.main, container,false);
            TextView tv = (TextView)view.findViewById(R.id.tv);
            tv.setText("TAB...");
            return view;
        }
        
       

    }
}
