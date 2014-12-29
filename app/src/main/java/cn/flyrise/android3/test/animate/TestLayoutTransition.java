/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-10-12 上午9:24:47
 */
package cn.flyrise.android3.test.animate;

import cn.flyrise.android3.test.R;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * 类功能描述：</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * 修改时间：</br>
 * 修改备注：</br>
 */
public class TestLayoutTransition extends Activity{
    ViewGroup container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout_transition);
        ViewGroup parent = (ViewGroup)findViewById(R.id.parent);
        
        container = new LinearLayout(this);
        container.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        for (int i = 0; i < 4; ++i) {
            Button newButton = new Button(this);
            newButton.setMinWidth(100);
            newButton.setText(String.valueOf(i));
            container.addView(newButton);
            newButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    v.setVisibility(View.GONE);
                }
            });
        }
        
        parent.addView(container);
        
        setupTransition();
        
        Button addBtn = (Button)findViewById(R.id.add);
        addBtn.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                for(int i = 0;i<container.getChildCount();i++){
                    container.getChildAt(i).setVisibility(View.VISIBLE);
                }
            }
        });
    }
    
    /**
     * 为View的变化添加动画
     */
    private void setupTransition(){
        LayoutTransition transition = new LayoutTransition();
        transition.setDuration(1000);
        
        ObjectAnimator removeAnimator = ObjectAnimator.ofFloat(null, "rotationX", 0f,90f);
        removeAnimator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                View view = (View)((ObjectAnimator)animation).getTarget();
                view.setRotationX(0f);
            }
        });
        //将该动画应用到将要消失的View
        transition.setAnimator(LayoutTransition.DISAPPEARING, removeAnimator);
        
        
        
        
        
        
        
        
        //pvhLeft,pvhTop
        PropertyValuesHolder pvhLeft =
                PropertyValuesHolder.ofInt("left",0);
        PropertyValuesHolder pvhTop =
                PropertyValuesHolder.ofInt("top", 0, 1);
        PropertyValuesHolder pvhRight =
                PropertyValuesHolder.ofInt("right", 0,1);
        PropertyValuesHolder pvhBottom =
                PropertyValuesHolder.ofInt("bottom", 0, 1);
        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofFloat("scaleX",1f, 0f, 1f,0f, 1f);
        PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofFloat("scaleY", 1f, 0f, 1f,0f, 1f);
        
        ObjectAnimator addAnimator = ObjectAnimator.ofPropertyValuesHolder(
                this, pvhLeft,pvhScaleX,pvhScaleY);
       
        
        
        
        transition.setAnimator(LayoutTransition.CHANGE_APPEARING, addAnimator);
        
        container.setLayoutTransition(transition);
    }

}
