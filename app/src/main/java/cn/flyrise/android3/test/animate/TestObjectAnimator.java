/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-1-18 ����4:57:36
 */
package cn.flyrise.android3.test.animate;

import cn.flyrise.android3.test.R;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * �๦��������</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * �޸�ʱ�䣺</br>
 * �޸ı�ע��</br>
 */
public class TestObjectAnimator extends Activity {
    Button btn,btn2 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.animate_1);
        
        btn = (Button)findViewById(R.id.btn);
        btn2 = (Button)findViewById(R.id.btn2);
        
        btn.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(btn2, "x",100,500);
                
                //ֻ��һ��ֵ�������ֵ��endValue��������Ҫ�ṩget<Proprty>�������ṩstartValue
               // ObjectAnimator animator = ObjectAnimator.ofFloat(btn2, "x",500);
                animator.setDuration(3000);
                animator.start();
                
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(btn, "alpha", 0f, 1f);
                animator2.setDuration(3000);
                animator2.start();
            }
        });
    }

}
