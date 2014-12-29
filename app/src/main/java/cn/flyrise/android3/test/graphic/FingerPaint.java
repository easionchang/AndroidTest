/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2014-11-3 ����4:52:08
 */
package cn.flyrise.android3.test.graphic;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

/**
 * �๦��������</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * �޸�ʱ�䣺</br>
 * �޸ı�ע��</br>
 */
public class FingerPaint extends Activity{

    private SimpleView myView = null;
    private boolean isSmooth = false;
    private EmbossMaskFilter mEmboss;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myView = new SimpleView(this);
        setContentView(myView);
        
        mEmboss = new EmbossMaskFilter(new float[] { 1, 1, 1 },
                0.4f, 6, 3.5f);
    }
    
    
    class SimpleView extends View{
        Bitmap mBitmap = null;
        Canvas mCanvas = null;
        Path mPath = new Path();
        Paint paint = null;
        Paint   mBitmapPaint;
        public SimpleView(Context context) {
            super(context);
            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setStrokeWidth(10);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.RED);
            paint.setStrokeCap(Paint.Cap.ROUND);
           
            mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawColor(0xFFAAAAAA);
            //����bitmap��֮ǰ�����Path
            canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
            //���Ƶ�ǰ��ָ���ڻ滭��Path
            canvas.drawPath(mPath, paint);
        }
        
        public void setSmooth(boolean isSmooth){
            if(isSmooth){
                //���߶θ�Բ����Ҳ�����ñ���������ʵ�֣���APIDEMO
                paint.setPathEffect(new CornerPathEffect(30));
            }else{
                paint.setPathEffect(null);
            }
        }
        
        public void setMaskFilter(EmbossMaskFilter filter){
            if(paint.getMaskFilter() == null){
                paint.setMaskFilter(filter);
            }else{
                paint.setMaskFilter(null);
            }
        }
        
        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(mBitmap);
        }
        
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();
            switch(event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    mPath.moveTo(x, y);
                    break;
                case MotionEvent.ACTION_MOVE:
                    mPath.lineTo(x, y);
                    invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    //��ָֹͣ�����˴λ��Ƶ�Path���浽bitmap��
                    //mCanvas.drawPath(mPath, paint);
                    mPath.reset();
                    invalidate();
                    break;
            }
            Log.e("Test", "onTouchEvent>>>>>>>>>>>>>>>>");
            return true;
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 1, "�����⻬����");
        menu.add(0, 2, 2, "����MaskFilter");
        return true;
    }
    
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.getItem(0);
        item.setTitle("�����⻬���� "+(isSmooth?"ON":"OFF"));
        return true;
    }
    
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        if(item.getItemId() == 1){
            isSmooth = !isSmooth;
            myView.setSmooth(isSmooth);
        }else if(item.getItemId() == 2){
            myView.setMaskFilter(mEmboss);
        }
        return true;
    }
}
