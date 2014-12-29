/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2014-9-9 ����2:23:03
 */
package cn.flyrise.android3.test.graphic;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Bundle;
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
public class CkeckerboardBitmapCreate extends Activity{
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }
    
    class MyView extends View{
        private Bitmap bm;
        private BitmapShader mBG;
        private Paint paint;

        public MyView(Context context) {
            super(context);
            bm = Bitmap.createBitmap(new int[] { 0xFFFFFFFF, 0xFFCCCCCC,
                    0xFFCCCCCC, 0xFFFFFFFF }, 2, 2,
                    Bitmap.Config.RGB_565);
            
            //�ظ���ģʽ
            mBG = new BitmapShader(bm,
                    Shader.TileMode.REPEAT,
                    Shader.TileMode.REPEAT);
            
            //��bm�Ŵ�20��
            Matrix m = new Matrix();
            m.setScale(20, 20);
            mBG.setLocalMatrix(m);
            
            paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setShader(mBG);
        }
        
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawRect(0,0,500,500, paint);
        }
        
    }

}
