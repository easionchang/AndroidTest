/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-10-30 下午2:06:10
 */
package cn.flyrise.android3.test.animate;

import cn.flyrise.android3.test.R;
import cn.flyrise.android3.test.animate.evaluator.FreefallEvaluator;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
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
public class FreefallAnimator extends Activity{
    
    public final static float G = 9.8f;
    public final static float G2 = 4.9f;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animate_1);
        
        Button btn = (Button)findViewById(R.id.btn);
        final Button btn2 = (Button)findViewById(R.id.btn2);
        
        btn.setOnClickListener(new OnClickListener() {
            
            public void onClick(View v) {
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(btn2, "y", 0f,700f);
                objectAnimator.setEvaluator(new FreefallEvaluator());
                objectAnimator.setDuration(FreefallEvaluator.getDuration(0f,700f));
                objectAnimator.start();
            }
        });
    }
    
}
