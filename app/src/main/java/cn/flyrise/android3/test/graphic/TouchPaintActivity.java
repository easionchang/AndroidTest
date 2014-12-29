/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2014-9-18 上午11:30:16
 */
package cn.flyrise.android3.test.graphic;



import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
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
public class TouchPaintActivity extends Activity{
    private static final int MSG_FADE = 1;
    private static final int FADE_DELAY = 100;
    PaintView mView;
    
    /** Colors to cycle through. */
    static final int[] COLORS = new int[] {
        Color.WHITE, Color.RED, Color.YELLOW, Color.GREEN,
        Color.CYAN, Color.BLUE, Color.MAGENTA,
    };

    /** Background color. */
    static final int BACKGROUND_COLOR = Color.BLACK;
    int mColorIndex;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = new PaintView(this);
        setContentView(mView);
        
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        startFading();
    }
    
    void startFading() {
        mHandler.removeMessages(MSG_FADE);
        scheduleFade();
    }
    
    void scheduleFade() {
        mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_FADE), FADE_DELAY);
    }
    

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                // Upon receiving the fade pulse, we have the view perform a
                // fade and then enqueue a new message to pulse at the desired
                // next time.
                case MSG_FADE: {
                    mView.fade();
                    scheduleFade();
                    break;
                }
                default:
                    super.handleMessage(msg);
            }
        }
    };
    
    class PaintView extends View{
        private static final int FADE_ALPHA = 0x06;
        private static final int MAX_FADE_STEPS = 256 / FADE_ALPHA + 4;
        Paint mPaint;
        Bitmap mBitmap;
        Canvas mCanvas;
        private int mFadeSteps = MAX_FADE_STEPS;
        
        private final Paint mFadePaint;
        int height;
        int width;

        public PaintView(Context context) {
            super(context);
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            

            mFadePaint = new Paint();
            mFadePaint.setColor(BACKGROUND_COLOR);
            mFadePaint.setAlpha(FADE_ALPHA);
        }
        
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            if (mBitmap != null) {
                canvas.drawBitmap(mBitmap, 0, 0, null);
            }
        }
        public void fade() {
            if (mCanvas != null && mFadeSteps < MAX_FADE_STEPS) {
                mCanvas.drawPaint(mFadePaint);
//                RectF rect = new RectF(0,0,width,height);
//                mCanvas.drawRect(rect, mFadePaint);
                invalidate();

                mFadeSteps++;
            }
        }
        
        
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            Log.e("Test","onSizeChanged=====w="+w+" h="+h+" oldw="+oldw+" oldh="+oldh);
            mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(mBitmap);
            height = h;
            width = w;
        }
        
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            PaintMode mode = PaintMode.Draw;
            final int action = event.getActionMasked();
            if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE
                    || action == MotionEvent.ACTION_HOVER_MOVE) {
                Log.e("Test", "action==="+action);
                final int N = event.getHistorySize();
                final int P = event.getPointerCount();
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < P; j++) {
                        paint(getPaintModeForTool(event.getToolType(j), mode),
                                event.getHistoricalX(j, i),
                                event.getHistoricalY(j, i),
                                event.getHistoricalPressure(j, i),
                                event.getHistoricalTouchMajor(j, i),
                                event.getHistoricalTouchMinor(j, i),
                                event.getHistoricalOrientation(j, i),
                                event.getHistoricalAxisValue(MotionEvent.AXIS_DISTANCE, j, i),
                                event.getHistoricalAxisValue(MotionEvent.AXIS_TILT, j, i));
                    }
                }
                for (int j = 0; j < P; j++) {
                    paint(getPaintModeForTool(event.getToolType(j), mode),
                            event.getX(j),
                            event.getY(j),
                            event.getPressure(j),
                            event.getTouchMajor(j),
                            event.getTouchMinor(j),
                            event.getOrientation(j),
                            event.getAxisValue(MotionEvent.AXIS_DISTANCE, j),
                            event.getAxisValue(MotionEvent.AXIS_TILT, j));
                }
                //mCurX = event.getX();
                //mCurY = event.getY();
            }
            return true;
        }
        
        private void paint(PaintMode mode, float x, float y, float pressure,
                float major, float minor, float orientation,
                float distance, float tilt) {
            if (mBitmap != null) {
                if (major <= 0 || minor <= 0) {
                    // If size is not available, use a default value.
                    major = minor = 16;
                }

                switch (mode) {
                    case Draw:
                        mPaint.setColor(COLORS[mColorIndex]);
                        mPaint.setAlpha(Math.min((int)(pressure * 128), 255));
                        drawOval(mCanvas, x, y, major, minor, orientation, mPaint);
                        break;

                    case Erase:
                        mPaint.setColor(BACKGROUND_COLOR);
                        mPaint.setAlpha(Math.min((int)(pressure * 128), 255));
                        drawOval(mCanvas, x, y, major, minor, orientation, mPaint);
                        break;

                    case Splat:
//                        mPaint.setColor(COLORS[mColorIndex]);
//                        mPaint.setAlpha(64);
//                        drawSplat(mCanvas, x, y, orientation, distance, tilt, mPaint);
                        break;
                }
            }
            mFadeSteps = 0;
            invalidate();
        }
        
        
        private PaintMode getPaintModeForTool(int toolType, PaintMode defaultMode) {
            if (toolType == MotionEvent.TOOL_TYPE_ERASER) {
                return PaintMode.Erase;
            }
            return defaultMode;
        }
        
        /**
         * Draw an oval.
         *
         * When the orienation is 0 radians, orients the major axis vertically,
         * angles less than or greater than 0 radians rotate the major axis left or right.
         */
        private final RectF mReusableOvalRect = new RectF();
        private void drawOval(Canvas canvas, float x, float y, float major, float minor,
                float orientation, Paint paint) {
            canvas.save(Canvas.MATRIX_SAVE_FLAG);
            canvas.rotate((float) (orientation * 180 / Math.PI), x, y);
            mReusableOvalRect.left = x - minor / 2;
            mReusableOvalRect.right = x + minor / 2;
            mReusableOvalRect.top = y - major / 2;
            mReusableOvalRect.bottom = y + major / 2;
            canvas.drawOval(mReusableOvalRect, paint);
            canvas.restore();
        }
        
    }

    enum PaintMode {
        Draw,
        Splat,
        Erase,
    }
}
