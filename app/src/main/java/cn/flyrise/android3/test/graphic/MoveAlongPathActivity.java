/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2014-10-21 下午2:38:20
 */
package cn.flyrise.android3.test.graphic;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PathMeasure;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * 类功能描述：</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * 修改时间：</br>
 * 修改备注：</br>
 */
public class MoveAlongPathActivity extends Activity{
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final MyView myView = new MyView(this);
        setContentView(myView);
        
        ValueAnimator animation =ValueAnimator.ofFloat(0f,1f); 
        animation.setDuration(3000);
        animation.start();
        
        animation.addUpdateListener(new AnimatorUpdateListener() {
            
            public void onAnimationUpdate(ValueAnimator animation) {
                myView.setParameter((Float)animation.getAnimatedValue());
                Log.e("Test", " animation.getAnimatedValue()=="+ animation.getAnimatedValue());
            }
        });
    }

    
    class MyView extends View{
        Path largerCircle;
        Path smallerCircle;
       
        PathMeasure largerMeasure;
        PathMeasure smallerMeasure;
        
        Paint p;
        
        float[] pos1 = new float[2];
        float[] pos2 = new float[2];
        public MyView(Context context) {
            super(context);
            p = new Paint();
            p.setStyle(Paint.Style.STROKE);
            p.setColor(Color.RED);
            
            largerCircle = new Path();
            largerCircle.moveTo(500, 500);
            largerCircle.addCircle(500, 500, 300, Direction.CW);
            largerMeasure = new PathMeasure();
            largerMeasure.setPath(largerCircle, true);
            
            smallerCircle = new Path();
            smallerCircle.moveTo(500, 500);
            smallerCircle.addCircle(500, 500, 100, Direction.CW);
            smallerMeasure = new PathMeasure();
            smallerMeasure.setPath(smallerCircle, true);
            
        }
        
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawColor(Color.WHITE);
            
            p.setColor(Color.RED);
            canvas.drawPath(largerCircle, p);
            p.setColor(Color.BLUE);
            canvas.drawPath(smallerCircle, p);
            
            p.setColor(Color.BLACK);
            canvas.drawLine(pos1[0], pos1[1], pos2[0], pos2[1], p);
            Log.e("Test", "pos1[0]="+pos1[0]+" pos1[1]="+pos1[1]+" pos2[0]="+pos2[0]+" pos2[1]="+pos2[1]);
        }
        
        public void setParameter(float percent){
            largerMeasure.getPosTan(largerMeasure.getLength()*percent, pos1, null);
            smallerMeasure.getPosTan(smallerMeasure.getLength()*percent, pos2, null);
            this.invalidate();
        }
        
    }
}
