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
public class FragmentAnimate3Activity extends Activity{
    int index = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_animate_3);
        
        getFragmentManager().beginTransaction()
        .add(R.id.fragment_layout, new MyFragment())
        .commit();
    }
    
    
    


    
    public static class MyFragment extends Fragment{
        
        public MyFragment(){
        }
        
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_animate_3_inner_fragment_1, container,false);
            Button btn = (Button)view.findViewById(R.id.next_step);
            btn.setOnClickListener(new OnClickListener() {
                
                @Override
                public void onClick(View v) {
                    
                }
            });
            return view;
        }
    }
    
  
}
