/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-9-25 上午11:01:01
 */
package cn.flyrise.android3.test.animate;

import cn.flyrise.android3.test.R;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.Toast;

/**
 * 类功能描述：</br>
 * 虽然按钮是移动了，但是点击事件却还是在原来移动之前的位置
 */
public class TestPropertyValueHolder extends Activity{
    Button btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animate_property_value_holder);
        
        Button btn = (Button)findViewById(R.id.btn);
        
        btn2 = (Button)findViewById(R.id.btn2);
        btn.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                startAnimate();
            }
        });
    }
    
    
    private void startAnimate(){
        //x=0 y=0  为起始的位置 ； 传入多余2个的参数为了让其折线运行
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("x",0f, 150f,150f); 
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("y",0f, 300f,0f); 
        
        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofFloat("scaleX",1f, 0f, 1f,0f, 1f);
        PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofFloat("scaleY",1f, 0f, 1f,0f, 1f);
        
        ObjectAnimator objAnimator = ObjectAnimator.ofPropertyValuesHolder(btn2, pvhX,pvhY,pvhScaleX,pvhScaleY);
        objAnimator.setDuration(5000);
        objAnimator.setInterpolator(new LinearInterpolator());
        objAnimator.start();
    }
    
    private void startAnimate2(){
        
        //btn2.setLeft(1);
        //btn2.setTop(1);
        
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofInt("left",0, 1); 
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofInt("top",0, 1); 
        PropertyValuesHolder pvhB = PropertyValuesHolder.ofInt("bottom",30, 50); 
        PropertyValuesHolder pvhR = PropertyValuesHolder.ofInt("roght",30, 50); 
        
        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofFloat("scaleX",0f, 1f); 
        PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofFloat("scaleY",0f, 1f); 
        
        ObjectAnimator objAnimator = ObjectAnimator.ofPropertyValuesHolder(btn2, pvhScaleX,pvhScaleY);
        objAnimator.setDuration(5000);
        objAnimator.start();
    }
    

}
