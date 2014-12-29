/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2014-9-11 下午1:28:04
 */
package cn.flyrise.android3.test.graphic;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
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
public class UnicodeChar extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new SampleView(this));
    }
    
    private static class SampleView extends View {
        private Paint mBigCharPaint;
        private Paint mLabelPaint;
        private final char[] mChars = new char[256];
        private final float[] mPos = new float[512];

        private int mBase;

        private static final int XMUL = 40;
        private static final int YMUL = 48;
        private static final int YBASE = 30;

        public SampleView(Context context) {
            super(context);
            setFocusable(true);
            setFocusableInTouchMode(true);

            mBigCharPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mBigCharPaint.setTextSize(30);
            mBigCharPaint.setTextAlign(Paint.Align.CENTER);

            mLabelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mLabelPaint.setTextSize(18);
            mLabelPaint.setTextAlign(Paint.Align.CENTER);

            // the position array is the same for all charts
            float[] pos = mPos;
            int index = 0;
            for (int col = 0; col < 16; col++) {
                final float x = col * XMUL + 10;
                for (int row = 0; row < 16; row++) {
                    pos[index++] = x;
                    pos[index++] = row * YMUL + YBASE;
                }
            }
        }

        private float computeX(int index) {
            //Log.e("Test", "index=="+index+"   index >> 4=="+(index >> 4));
            return (index >> 4) * XMUL + 10;
        }

        private float computeY(int index) {
            Log.e("Test", "index=="+index+"   index & 0xF=="+(index & 0xF));
            return (index & 0xF) * YMUL + YMUL;
        }

        private void drawChart(Canvas canvas, int base) {
            char[] chars = mChars;
            for (int i = 0; i < 256; i++) {
                int unichar = base + i;
                chars[i] = (char)unichar;

                canvas.drawText(Integer.toHexString(unichar),
                                computeX(i), computeY(i), mLabelPaint);
            }
            canvas.drawPosText(chars, 0, 256, mPos, mBigCharPaint);
        }

        @Override protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.WHITE);

            canvas.translate(0, 1);
            drawChart(canvas, mBase * 256);
        }

        @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_DPAD_LEFT:
                    if (mBase > 0) {
                        mBase -= 1;
                        invalidate();
                    }
                    return true;
                case KeyEvent.KEYCODE_DPAD_RIGHT:
                    mBase += 1;
                    invalidate();
                    return true;
                default:
                    break;
            }
            return super.onKeyDown(keyCode, event);
        }
    }
}
