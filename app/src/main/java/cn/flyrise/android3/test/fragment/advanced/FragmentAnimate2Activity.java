/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-3-6 上午9:43:53
 */
package cn.flyrise.android3.test.fragment.advanced;

import cn.flyrise.android3.test.R;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * 类功能描述：</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * 修改时间：</br>
 * 修改备注：</br>
 */
public class FragmentAnimate2Activity extends Activity{
    int index = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_animate_2);
        
        
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem item = menu.add(0, 0, 0, "ADD");
        item.setIcon(android.R.drawable.ic_menu_add);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        
        
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        
        if(item.getItemId() == 0){
            addNo();
        }
        return true;
    }
    
    private void addNo(){
        getFragmentManager().beginTransaction()
        .add(R.id.fragment_layout, new MyFragment(index))
        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        .commit();
        index++;
    }
    
    public void deleteNo(MyFragment fragment){
        getFragmentManager().beginTransaction()
        .remove(fragment)
        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        .commit();
    }
    
    
    public static class MyFragment extends Fragment{
        
        private int mIndex;
        private TextView phoneTipTv;
        private Button phoneTipBtn;
        
        public MyFragment(int index){
            mIndex = index;
        }
        
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_animate_2_inner_fragment, container,false);
            phoneTipTv = (TextView)view.findViewById(R.id.phone_tip);
            phoneTipTv.setText("电话号码"+mIndex);
            
            phoneTipBtn = (Button)view.findViewById(R.id.phone_delete);
            phoneTipBtn.setOnClickListener(new OnClickListener() {
                
                @Override
                public void onClick(View v) {
                    FragmentAnimate2Activity activity = (FragmentAnimate2Activity)MyFragment.this.getActivity();
                    activity.deleteNo(MyFragment.this);
                }
            });
            return view;
        }
    }
    
  
}
