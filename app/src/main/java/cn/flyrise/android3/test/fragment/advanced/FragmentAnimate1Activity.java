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
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 类功能描述：</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * 修改时间：</br>
 * 修改备注：</br>
 */
public class FragmentAnimate1Activity extends Activity{
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_animate_1);
        
        Button btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                Fragment myFragment = getFragmentManager().findFragmentById(R.id.frgment_layout);
                if(myFragment == null){
                    getFragmentManager().beginTransaction()
                    .add(R.id.frgment_layout, new MyFragment())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit();
                    
                }else{
                    //getFragmentManager().beginTransaction().remove(myFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                    getFragmentManager().popBackStack();
                }
               
            }
        });
    }
    
    
    public static class MyFragment extends Fragment{

        
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_animate_1_inner_fragment, container, false);
            return view;
        }
    }

}
