/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-1-18 下午1:29:11
 */
package cn.flyrise.android3.test.animate;

import cn.flyrise.android3.test.R;

import android.animation.Animator;
import android.animation.TypeEvaluator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
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
public class TestValueAnimator extends Activity{
    public static String TAG = "TestValueAnimator";
    Button btn,btn2 ;
    MyView myView;
    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         
        setContentView(R.layout.animate_1);
        layout = (LinearLayout)findViewById(R.id.container);
        
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        
        myView = new MyView(this);
        layout.addView(myView, params);
        
       btn = (Button)findViewById(R.id.btn);
       btn2 = (Button)findViewById(R.id.btn2);
       
       btn.setOnClickListener(new OnClickListener() {
        
        @Override
        public void onClick(View v) {
            ValueAnimator animator = ValueAnimator.ofInt(100,layout.getWidth());
            ValueAnimator animator2 = ValueAnimator.ofInt(100,layout.getHeight());
            
            
          
            animator.setDuration(2000);
            animator2.setDuration(2000);
            
            animator.setInterpolator(new BounceInterpolator());
            animator2.setInterpolator(new AccelerateInterpolator(9.8f)); //加速度为9.8重力加速度
            
            animator.start();
            animator2.start();

            animator.addUpdateListener(new AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator animation) {
                    //Log.e(TAG, animation.getAnimatedValue()+">>>>>>>myView.getWidth()=="+myView.getWidth());
                    int x = (Integer)animation.getAnimatedValue();
                    
                    myView.layout(x - myView.getWidth(), myView.getTop(), x,myView.getBottom());
                }
            });
            
            animator2.addUpdateListener(new AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator animation) {
                    //Log.e(TAG, animation.getAnimatedValue()+">>>>>>>myView.getWidth()=="+myView.getWidth());
                    int y = (Integer)animation.getAnimatedValue();
                    
                    btn2.layout(btn2.getLeft(), y - btn2.getHeight(), btn2.getRight(),y);
                }
            });
            
            
            
            
   
           
            
//            animator.addListener(new AnimatorListener() {
//                public void onAnimationStart(Animator animation) { }
//                
//                public void onAnimationRepeat(Animator animation) { }
//                
//                public void onAnimationEnd(Animator animation) { }
//                
//                public void onAnimationCancel(Animator animation) { }
//            });
            
        }
    });
    }

    
    class MyView extends View{
        ShapeDrawable circleDrable = null;
        public MyView(Context context) {
            super(context);
            OvalShape circle = new OvalShape();
            circle.resize(50, 50);
            
            circleDrable = new ShapeDrawable(circle);
           
            Paint paint = circleDrable.getPaint(); //new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setARGB(255, 0, 0, 255);
         
        }
        
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            circleDrable.draw(canvas);
        }
        
        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            setMeasuredDimension(50, 50);
        }
        
    }
}
