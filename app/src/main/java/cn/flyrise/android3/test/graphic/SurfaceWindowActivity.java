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
import android.util.Log;
import android.view.SurfaceHolder;
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
public class SurfaceWindowActivity extends Activity implements SurfaceHolder.Callback2{
    DrawingThread mDrawingThread;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Tell the activity's window that we want to do our own drawing
        // to its surface.  This prevents the view hierarchy from drawing to
        // it, though we can still add views to capture input if desired.
        getWindow().takeSurface(this);
        
        // This is the thread that will be drawing to our surface.
        mDrawingThread = new DrawingThread();
        mDrawingThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        
        // Make sure the drawing thread is not running while we are paused.
        synchronized (mDrawingThread) {
            mDrawingThread.mRunning = false;
            mDrawingThread.notify();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        
        // Let the drawing thread resume running.
        synchronized (mDrawingThread) {
            mDrawingThread.mRunning = true;
            mDrawingThread.notify();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        
        // Make sure the drawing thread goes away.
        synchronized (mDrawingThread) {
            mDrawingThread.mQuit = true;
            mDrawingThread.notify();
        }
    }

    public void surfaceCreated(SurfaceHolder holder) {
        // Tell the drawing thread that a surface is available.
        synchronized (mDrawingThread) {
            mDrawingThread.mSurface = holder;
            mDrawingThread.notify();
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // Don't need to do anything here; the drawing thread will pick up
        // new sizes from the canvas.
    }

    public void surfaceRedrawNeeded(SurfaceHolder holder) {
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // We need to tell the drawing thread to stop, and block until
        // it has done so.
        synchronized (mDrawingThread) {
            mDrawingThread.mSurface = holder;
            mDrawingThread.notify();
            while (mDrawingThread.mActive) {
                try {
                    mDrawingThread.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Tracking of a single point that is moving on the screen.
    static final class MovingPoint {
        float x, y, dx, dy;
        
        void init(int width, int height, float minStep) {
            x = (float)((width-1)*Math.random());
            y = (float)((height-1)*Math.random());
            dx = (float)(Math.random()*minStep*2) + 1;
            dy = (float)(Math.random()*minStep*2) + 1;
            Log.e("Test", "x="+x+" y="+y+" dx="+dx+" dy="+dy);
        }
        
        float adjDelta(float cur, float minStep, float maxStep) {
            cur += (Math.random()*minStep) - (minStep/2);
            if (cur < 0 && cur > -minStep) cur = -minStep;
            if (cur >= 0 && cur < minStep) cur = minStep;
            if (cur > maxStep) cur = maxStep;
            if (cur < -maxStep) cur = -maxStep;
            return cur;
        }
        //每次线条移动固定的位置
        void step(int width, int height, float minStep, float maxStep) {
            x += dx;
            if (x <= 0 || x >= (width-1)) {
                if (x <= 0) x = 0;
                else if (x >= (width-1)) x = width-1;
                dx = adjDelta(-dx, minStep, maxStep);
            }
            y += dy;
            if (y <= 0 || y >= (height-1)) {
                if (y <= 0) y = 0;
                else if (y >= (height-1)) y = height-1;
                dy = adjDelta(-dy, minStep, maxStep);
            }
        }
    }
    
    /**
     * This is a thread that will be running a loop, drawing into the
     * window's surface.
     */
    class DrawingThread extends Thread {
        // These are protected by the Thread's lock.
        SurfaceHolder mSurface;
        boolean mRunning;
        boolean mActive;
        boolean mQuit;
        
        // Internal state.
        int mLineWidth;
        float mMinStep;
        float mMaxStep;
        
        boolean mInitialized;
        final MovingPoint mPoint1 = new MovingPoint();
        final MovingPoint mPoint2 = new MovingPoint();
        //记录100条历史数据（线条）
        static final int NUM_OLD = 100;
        int mNumOld = 0;
        //记录100条历史线条的开始和结束XY坐标值
        final float[] mOld = new float[NUM_OLD*4];
        final int[] mOldColor = new int[NUM_OLD];
        int mBrightLine = 0;//亮色线条位置
        
        // X is red, Y is blue.
        final MovingPoint mColor = new MovingPoint();
        
        final Paint mBackground = new Paint();
        final Paint mForeground = new Paint();
        
        int makeGreen(int index) {
            //mBrightLine在亮色线条前后的十个线条才会有颜色变亮效果
            int dist = Math.abs(mBrightLine-index);
            if (dist > 10) return 0;
            return (255-(dist*(255/10))) << 8;
        }
        
        @Override
        public void run() {
            mLineWidth = (int)(getResources().getDisplayMetrics().density * 1.5);
            if (mLineWidth < 1) mLineWidth = 1;
            mMinStep = mLineWidth * 2;
            mMaxStep = mMinStep * 3;
            Log.e("Test", "mMinStep="+mMinStep+" mMaxStep="+mMaxStep+" mLineWidth="+mLineWidth);
            
            mBackground.setColor(0xff000000);
            mForeground.setColor(0xff00ffff);
            mForeground.setAntiAlias(false);
            mForeground.setStrokeWidth(mLineWidth);
            
            while (true) {
                // Synchronize with activity: block until the activity is ready
                // and we have a surface; report whether we are active or inactive
                // at this point; exit thread when asked to quit.
                synchronized (this) {
                    while (mSurface == null || !mRunning) {
                        if (mActive) {
                            mActive = false;
                            notify();
                        }
                        if (mQuit) {
                            return;
                        }
                        try {
                            wait();
                        } catch (InterruptedException e) {
                        }
                    }
                    
                    if (!mActive) {
                        mActive = true;
                        notify();
                    }
                    
                    // Lock the canvas for drawing.
                    Canvas canvas = mSurface.lockCanvas();
                    if (canvas == null) {
                        Log.i("WindowSurface", "Failure locking canvas");
                        continue;
                    }
                    
                    // Update graphics.
                    if (!mInitialized) {
                        mInitialized = true;
                        mPoint1.init(canvas.getWidth(), canvas.getHeight(), mMinStep);
                        mPoint2.init(canvas.getWidth(), canvas.getHeight(), mMinStep);
                        mColor.init(127, 127, 1);
                    } else {
                        mPoint1.step(canvas.getWidth(), canvas.getHeight(),
                                mMinStep, mMaxStep);
                        mPoint2.step(canvas.getWidth(), canvas.getHeight(),
                                mMinStep, mMaxStep);
                        mColor.step(127, 127, 1, 3);
                    }
                    //颜色变亮的线条位置后移，形成动态效果
                    mBrightLine+=2;
                    if (mBrightLine > (NUM_OLD*2)) {
                        mBrightLine = -2;
                    }
                    Log.e("Test", "mBrightLine=="+mBrightLine);
                    // Clear background.
                    canvas.drawColor(mBackground.getColor());
                    
                    // Draw old lines.绘出旧的线条数据
                    for (int i=mNumOld-1; i>=0; i--) {
                        mForeground.setColor(mOldColor[i] | makeGreen(i));
                        mForeground.setAlpha(((NUM_OLD-i) * 255) / NUM_OLD);
                        int p = i*4;
                        canvas.drawLine(mOld[p], mOld[p+1], mOld[p+2], mOld[p+3], mForeground);
                    }
                    
                    // Draw new line.绘出一个新的线条数据
                    int red = (int)mColor.x + 128;
                    if (red > 255) red = 255;
                    int blue = (int)mColor.y + 128;
                    if (blue > 255) blue = 255;
                    int color = 0xff000000 | (red<<16) | blue;
                    mForeground.setColor(color | makeGreen(-2));
                    //Log.e("Test", "mPoint1.x="+mPoint1.x+" mPoint1.y="+mPoint1.y+" mPoint2.x="+mPoint2.x+" mPoint2.y="+mPoint2.y);
                    canvas.drawLine(mPoint1.x, mPoint1.y, mPoint2.x, mPoint2.y, mForeground);
                    
                    // Add in the new line.将最旧的一个线条移除，添加最新的一个线条
                    if (mNumOld > 1) {
                        System.arraycopy(mOld, 0, mOld, 4, (mNumOld-1)*4);
                        System.arraycopy(mOldColor, 0, mOldColor, 1, mNumOld-1);
                    }
                    if (mNumOld < NUM_OLD) mNumOld++;
                    mOld[0] = mPoint1.x;
                    mOld[1] = mPoint1.y;
                    mOld[2] = mPoint2.x;
                    mOld[3] = mPoint2.y;
                    mOldColor[0] = color;
                    
                    // All done!
                    mSurface.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
