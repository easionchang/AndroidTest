/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2014-9-23 上午11:41:37
 */
package cn.flyrise.android3.test.graphic;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Typeface;
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
public class TextAlignActivity extends Activity{
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new SampleView(this));
    }
    
    
    static class SampleView extends View{
        private static final int DY = 30;
        private static final String TEXT_L = "Left";
        private static final String TEXT_C = "Center";
        private static final String TEXT_R = "Right";
        private static final String POSTEXT = "Positioned";
        private static final String TEXTONPATH = "Along a path";
        
        Paint mPaint,mPathPaint ;
        float mX;
        float[] mPos;
        Path mPath;
        
        public SampleView(Context context) {
            super(context);
            
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setTextSize(30);
            mPaint.setTypeface(Typeface.SERIF);
            
            
            mPos = buildTextPositions(POSTEXT, 0, mPaint);
            
            
            mPath = new Path();
            makePath(mPath);
            
            mPathPaint = new Paint();
            mPathPaint.setAntiAlias(true);
            mPathPaint.setColor(0x800000FF);
            mPathPaint.setStyle(Paint.Style.STROKE);
        }
        
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawColor(Color.WHITE);
            
            mPaint.setColor(0x80FF0000);
            float x = mX;
            float y = 0;
          
            canvas.drawLine(x, y, x, y+DY*3, mPaint);
            mPaint.setColor(Color.BLACK);
            canvas.translate(0, DY);
            mPaint.setTextAlign(Paint.Align.LEFT);
            canvas.drawText(TEXT_L, x, y, mPaint);
            
            canvas.translate(0, DY);
            mPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(TEXT_C, x, y, mPaint);
            
            canvas.translate(0, DY);
            mPaint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText(TEXT_R, x, y, mPaint);
            
            
            
            canvas.translate(100, DY*2);
            mPaint.setColor(0xBB00FF00);
            
            for (int i = 0; i < mPos.length/2; i++) {
                canvas.drawLine(mPos[i*2+0], mPos[i*2+1]-DY,
                        mPos[i*2+0], mPos[i*2+1]+DY*2, mPaint);
            }
            
            mPaint.setColor(Color.BLACK);
            mPaint.setTextAlign(Paint.Align.LEFT);
            canvas.drawPosText(POSTEXT, mPos, mPaint);
            
            canvas.translate(0, DY);
            mPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawPosText(POSTEXT, mPos, mPaint);
            
            canvas.translate(0, DY);
            mPaint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawPosText(POSTEXT, mPos, mPaint);
            
            
            canvas.drawPath(mPath, mPathPaint);
            mPaint.setTextAlign(Paint.Align.LEFT);
            canvas.drawTextOnPath(TEXTONPATH, mPath, 0, 0, mPaint);
        }
        
        private float[] buildTextPositions(String text,float y,Paint p){
            float[] widths = new float[text.length()];
            
            int n = p.getTextWidths(text, widths);
            
            float accumulatedX = 0;
            float[] pos = new float[n * 2];
            for (int i = 0; i < n; i++) {
                pos[i*2] = accumulatedX;
                pos[i*2+1] = y;
                accumulatedX += widths[i];
            }
            return pos;
        }
        
        private static void makePath(Path p) {
            p.moveTo(100, 0);
            //p.cubicTo(100, -50, 200, 50, 300, 0);
            p.addCircle(50, 50, 30, Direction.CW);
        }
        
        @Override
        protected void onSizeChanged(int w, int h, int ow, int oh) {
            super.onSizeChanged(w, h, ow, oh);
            mX = w * 0.5f;  // remember the center of the screen
        }
        
    }
    

}
