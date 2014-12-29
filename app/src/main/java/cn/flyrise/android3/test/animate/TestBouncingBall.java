/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-1-22 下午2:18:47
 */
package cn.flyrise.android3.test.animate;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

/**
 * 类功能描述：</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * 修改时间：</br>
 * 
 * 修改备注：</br>
 */
public class TestBouncingBall extends Activity{
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyBallView(this));
       
        //addContentView(new MyBallView(this), params);
//        
//        LinearLayout container = new LinearLayout(this);
//        LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
//        container.setLayoutParams(params);
//        
//        container.addView(new MyBallView(this));
//        
//        setContentView(container);
    }
    
    
    class MyBallView extends View{

        private static final int RED = 0xffFF8080;
        private static final int BLUE = 0xff8080FF;
        int radius = 50;
        
        ShapeDrawable circleDrable;
        ShapeHolder holer = new ShapeHolder();
        
        int width;
        int height;
        
        public MyBallView(Context context) {
            super(context);
            
            
            
            
            ValueAnimator colorAnim = ObjectAnimator.ofInt(this, "backgroundColor", RED, BLUE);
            colorAnim.setDuration(3000);
            colorAnim.setEvaluator(new ArgbEvaluator());
            colorAnim.setRepeatCount(ValueAnimator.INFINITE);
            colorAnim.setRepeatMode(ValueAnimator.REVERSE);
            colorAnim.start();
            
            
            
            OvalShape circleShap = new OvalShape();
            circleShap.resize(radius, radius);
            
            circleDrable = new ShapeDrawable(circleShap);
            circleDrable.getPaint().setColor(Color.DKGRAY);
            holer.setDrawable(circleDrable);
           
            this.setOnTouchListener(new OnTouchListener() {
                
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    //circleDrable.setBounds(x-radius, y-radius, x, y);
                    
                    holer.setX((int)event.getX()) ;
                    
                    ObjectAnimator animator = ObjectAnimator.ofInt(holer,"y",(int)event.getY(),height-radius);
                    animator.setInterpolator(new AccelerateInterpolator());
                    animator.setDuration(800);
                    animator.setRepeatCount(1);
                    animator.setRepeatMode(ValueAnimator.REVERSE);
                    animator.start();
                    
                    animator.addUpdateListener(new AnimatorUpdateListener() {
                        public void onAnimationUpdate(ValueAnimator animation) {
                            //MyBallView.this.invalidate();
                            
                        }
                    });
                    return true;
                }
            });
        }
     
        
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            if(width == 0){
                width = (int)getWidth();
                height = (int)getHeight();
            }
            canvas.save();
            canvas.translate(holer.getX(), holer.getY());
            holer.getDrawable().draw(canvas);
            canvas.restore();
            //holer.getDrawable().setBounds(holer.getX(), holer.getY(),holer.getX()+radius, holer.getY()+radius);
          
            Log.e("Test", "left======"+holer.getX()+"   top========"+holer.getY()+"  right====="+holer.getX()+radius+"  bottom======="+(holer.getY()+radius)+"       y==========="+holer.getY());
        }
        
    }
    
    
    
    class ShapeHolder{
        private ShapeDrawable drawable;
        private int x;
        private int y;
        public ShapeDrawable getDrawable() {
            return drawable;
        }
        public void setDrawable(ShapeDrawable drawable) {
            this.drawable = drawable;
        }
        public int getX() {
            return x;
        }
        public void setX(int x) {
            this.x = x;
        }
        public int getY() {
            return y;
        }
        public void setY(int y) {
            this.y = y;
        }
    }

}
