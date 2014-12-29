/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2014-9-23 上午11:41:37
 */
package cn.flyrise.android3.test.graphic;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.SweepGradient;
import android.os.Bundle;
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
public class SweepActivity extends Activity{
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new SampleView(this));
    }
    
    
    static class SampleView extends View{
        int x = 200;
        int y = 300;
        Paint p;
        int mRotate;
        
        Matrix matrix = new Matrix();
        SweepGradient shader;

        /**
         * @param context
         */
        public SampleView(Context context) {
            super(context);
            p = new Paint();
            p.setAntiAlias(true);
            
           
            shader = new SweepGradient(x, y, new int[] { Color.GREEN,
                    Color.RED,
                    Color.BLUE,
                    Color.GREEN }, null);
            p.setShader(shader);
        }
        
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawColor(Color.WHITE);
            
            matrix.setRotate(mRotate,x,y);
            shader.setLocalMatrix(matrix);
            mRotate+=3;
            if(mRotate > 360){
                mRotate = 0;
            }
           
            canvas.drawCircle(x, y, 80, p);
            //Rect rect = new Rect(x-40, y-40, x+40, y+40);
            //canvas.drawRect(rect, p);
            
            invalidate();
        }

    }
    

}
