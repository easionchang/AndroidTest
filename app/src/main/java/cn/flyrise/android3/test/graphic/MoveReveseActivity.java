/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2014-10-21 ����2:38:20
 */
package cn.flyrise.android3.test.graphic;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PathMeasure;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;

/**
 * �๦��������</br>
 * ֪����һ��Ĺ켣���˳��ִ��ʣ��һ������й켣
 */
public class MoveReveseActivity extends Activity{
    int width;
    int height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Display mDisplay = getWindowManager().getDefaultDisplay();
        width = mDisplay.getWidth();
        height = mDisplay.getHeight();
        final MyView myView = new MyView(this);
        setContentView(myView);
        ValueAnimator animation1 =ValueAnimator.ofFloat(0f,0.5f); //ֻ����һ��
        animation1.setDuration(1500);
        animation1.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                myView.setParameter((Float)animation.getAnimatedValue());
                Log.e("Test", " animation.getAnimatedValue()=="+ animation.getAnimatedValue());
            }
        });
        
        animation1.addListener(new AnimatorListener() {
            public void onAnimationCancel(Animator animation) {}
            public void onAnimationStart(Animator animation) {}
            public void onAnimationRepeat(Animator animation) {}           
            public void onAnimationEnd(Animator animation) {
                myView.setReverse(true);
            }
        });
        
        
        ValueAnimator animation2 =ValueAnimator.ofFloat(0f,0.5f); //ֻ����һ��
        animation2.setDuration(1500);        
        animation2.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                myView.setParameter((Float)animation.getAnimatedValue());
                Log.e("Test", " animation.getAnimatedValue()=="+ animation.getAnimatedValue());
            }
        });
        
        
        AnimatorSet bouncer = new AnimatorSet();
        bouncer.play(animation1).before(animation2);
        bouncer.start();
    }

    
    class MyView extends View{
        Path largerCircle;
        Path smallerCircle;
        float percent;
        PathMeasure largerMeasure;
        PathMeasure smallerMeasure;
        
        Paint p;
        float[] pos1 = new float[2];
        float[] pos2 = new float[2];
        boolean reverse = false;
        
        public void setReverse(boolean reverse) {
            this.reverse = reverse;
        }

        public MyView(Context context) {
            super(context);
            p = new Paint();
            p.setStyle(Paint.Style.STROKE);
            p.setColor(Color.RED);
            
            largerCircle = new Path();
            largerCircle.moveTo(width/2, height/2);
            largerCircle.addCircle(width/2, height/2, 300, Direction.CW);
            largerMeasure = new PathMeasure();
            largerMeasure.setPath(largerCircle, true);
            
            smallerCircle = new Path();
            smallerCircle.moveTo(width/2, height/2);
            smallerCircle.addCircle(width/2, height/2, 100, Direction.CW);
            smallerMeasure = new PathMeasure();
            smallerMeasure.setPath(smallerCircle, true);
            
        }
        
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.save();
            
            canvas.drawColor(Color.WHITE);
            p.setColor(Color.RED);
            canvas.drawPath(largerCircle, p);
            p.setColor(Color.BLUE);
            canvas.drawPath(smallerCircle, p);
            
            if(reverse){//ͨ������ı仯���Կ��ƶ������˶��켣
                Matrix matrix = new Matrix();
                //matrix.setScale(-1, 1, width/2, height/2);//����
                //matrix.scale(-1, -1, width/2, height/2);//
                //canvas.concat(matrix);
                canvas.scale(-1, -1, width/2, height/2);//�����Ǽ�����ʱ���˶�һ����ͨ���仯x,y�ֱ����-1��ֵ���Կ������������й켣 
            }
            p.setColor(Color.BLACK);
            canvas.drawLine(pos1[0], pos1[1], pos2[0], pos2[1], p);
            //Log.e("Test", "pos1[0]="+pos1[0]+" pos1[1]="+pos1[1]+" pos2[0]="+pos2[0]+" pos2[1]="+pos2[1]);
            
            canvas.restore();
        }
        
        public void setParameter(float percent){
            this.percent = percent;

            largerMeasure.getPosTan(largerMeasure.getLength()*percent, pos1, null);
            smallerMeasure.getPosTan(smallerMeasure.getLength()*percent, pos2, null);
            this.invalidate();
        }
        
    }
}
