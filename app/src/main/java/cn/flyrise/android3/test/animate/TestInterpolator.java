/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-9-26 下午4:14:54
 */
package cn.flyrise.android3.test.animate;

import cn.flyrise.android3.test.R;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
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
public class TestInterpolator extends Activity{
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animate_2);
        
        final Button btn10 = (Button)findViewById(R.id.btn10);
        
        Button btn = (Button)findViewById(R.id.btn);
        btn.setText("Accelerate");
        btn.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(btn10, "x",0,500);
                animator.setInterpolator(new AccelerateInterpolator());
                animator.start();
            }
        });
        
        Button btn2 = (Button)findViewById(R.id.btn2);
        btn2.setText("AccelerateDecelerate");
        btn2.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(btn10, "x",0,500);
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.start();
            }
        });
        
        
        Button btn3 = (Button)findViewById(R.id.btn3);
        btn3.setText("Anticipate");
        btn3.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(btn10, "x",0,500);
                animator.setInterpolator(new AnticipateInterpolator());
                animator.start();
            }
        });
        
        
        Button btn4 = (Button)findViewById(R.id.btn4);
        btn4.setText("AnticipateOvershoot");
        btn4.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(btn10, "x",0,500);
                animator.setInterpolator(new AnticipateOvershootInterpolator());
                animator.start();
            }
        });
        
        
        Button btn5 = (Button)findViewById(R.id.btn5);
        btn5.setText("Bounce");
        btn5.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(btn10, "x",0,500);
                animator.setInterpolator(new BounceInterpolator());
                animator.start();
            }
        });
        
        
        Button btn6 = (Button)findViewById(R.id.btn6);
        btn6.setText("CycleInterpolator");
        btn6.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(btn10, "x",0,500);
                animator.setInterpolator(new CycleInterpolator(10f));
                animator.start();
            }
        });
        
        
        Button btn7 = (Button)findViewById(R.id.btn7);
        btn7.setText("DecelerateInterpolator");
        btn7.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(btn10, "x",0,500);
                animator.setInterpolator(new DecelerateInterpolator());
                animator.start();
            }
        });
        
        
        Button btn8 = (Button)findViewById(R.id.btn8);
        btn8.setText("LinearInterpolator");
        btn8.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(btn10, "x",0,500);
                animator.setInterpolator(new LinearInterpolator());
                animator.start();
            }
        });
        
        Button btn9 = (Button)findViewById(R.id.btn9);
        btn9.setText("OvershootInterpolator");
        btn9.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(btn10, "x",0,500);
                animator.setInterpolator(new OvershootInterpolator());
                animator.start();
            }
        });
    }

}
