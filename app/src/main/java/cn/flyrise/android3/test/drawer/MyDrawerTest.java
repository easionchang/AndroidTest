/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2012-11-16 下午02:45:58
 */
package cn.flyrise.android3.test.drawer;

import cn.flyrise.android3.test.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
public class MyDrawerTest extends Activity{
    View view;
    View panel;
    
    boolean push = false;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_drawer);
        
        view = findViewById(R.id.parent);
        panel = findViewById(R.id.panel);
        
        Button btn = (Button)findViewById(R.id.btn);  
        btn.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {  
                if(push){
                    view.setPadding(0, view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
                    Log.e("MyDrawerTest", ">>>>"+view.getPaddingLeft());
                }else{
                    view.setPadding(panel.getWidth()*2*-1 + 5, view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
                    Log.e("MyDrawerTest", "----"+panel.getWidth()*2*-1 + 5);
                }
                
                push = !push;
                
            }
        });
    }

}
