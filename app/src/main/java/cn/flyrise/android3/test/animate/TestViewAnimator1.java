/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-9-25 上午11:01:01
 */
package cn.flyrise.android3.test.animate;

import cn.flyrise.android3.test.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.Toast;

/**
 * 类功能描述：</br>
 * 虽然按钮是移动了，但是点击事件却还是在原来移动之前的位置
 */
public class TestViewAnimator1 extends Activity{
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animate_1);
        Button btn = (Button)findViewById(R.id.btn);
        final Button btn2 = (Button)findViewById(R.id.btn2);
        
       
        btn.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                TranslateAnimation translate = new TranslateAnimation(100,150,0,0);
                translate.setDuration(1000);
                translate.setFillAfter(true);
                btn2.setAnimation(translate);
                translate.start();
            }
        });
        
        btn2.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
               Toast.makeText(TestViewAnimator1.this, "DDDDD", Toast.LENGTH_SHORT).show();
                
            }
        });
    }
    
    

}
