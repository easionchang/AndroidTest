/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-1-22 下午1:47:53
 */
package cn.flyrise.android3.test.animate;

import cn.flyrise.android3.test.R;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
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
public class TestAnimatorSetActivity extends Activity{
    
    Button btn,btn2 ;
    LinearLayout layout;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animate_1);
        layout = (LinearLayout)findViewById(R.id.container);
        btn = (Button)findViewById(R.id.btn);
        btn2 = (Button)findViewById(R.id.btn2);
        
        btn.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                ValueAnimator animator = ObjectAnimator.ofFloat(btn2,"x",100,layout.getWidth()-btn2.getWidth());
                ValueAnimator animator2 = ObjectAnimator.ofFloat(btn2,"y",100,layout.getHeight()-btn2.getHeight());
                
                animator.setDuration(2000);
                animator2.setDuration(2000);
                
                //animator.start();
                
                AnimatorSet set = new AnimatorSet();
                //set.play(animator).before(animator2);
                
                set.play(animator).with(animator2);
                set.start();
                
            }
        });
    }

}
