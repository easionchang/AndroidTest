/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2014-10-21 上午10:13:15
 */
package cn.flyrise.android3.test.graphic;

import static android.graphics.Color.BLACK;
import static android.graphics.Paint.ANTI_ALIAS_FLAG;
import static android.graphics.Paint.SUBPIXEL_TEXT_FLAG;
import static android.graphics.Paint.Cap.BUTT;
import static android.graphics.Paint.Cap.ROUND;
import static android.graphics.Paint.Style.STROKE;
import static java.lang.Math.sqrt;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.Paint.Cap;
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
public class TestBezierActivity extends Activity{
    private  ValueAnimator animation;
    /** Paths were generated at a 3px/dp density; this is the scale factor for different densities. */
    private final static float PATH_GEN_DENSITY = 3;

    /** Paths were generated with at this size for {@link DrawerArrowDrawable#PATH_GEN_DENSITY}. */
    private final static float DIMEN_DP = 23.5f;

    /**
     * Paths were generated targeting this stroke width to form the arrowhead properly, modification
     * may cause the arrow to not for nicely.
     */
    private final static float STROKE_WIDTH_DP = 2;
    
    float density;
    
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        density = this.getResources().getDisplayMetrics().density;
        final MyView myView = new MyView(this);
        this.setContentView(myView);
        
        animation =ValueAnimator.ofFloat(0f,1f); 
        animation.setDuration(1500);
        animation.setRepeatCount(ValueAnimator.INFINITE);
        animation.setRepeatMode(ValueAnimator.REVERSE);
        animation.start();
      
        animation.addUpdateListener(new AnimatorUpdateListener() {
            
            public void onAnimationUpdate(ValueAnimator animation) {
                myView.setParameter((Float)animation.getAnimatedValue());
                Log.e("Test", " animation.getAnimatedValue()=="+ animation.getAnimatedValue());
            }
        });
    };
    
    @Override
    protected void onStop() {
        super.onPause();
        animation.cancel();
    }
    
    class MyView extends View{
        private float parameter;
        
        private BridgingLine line1;
        private BridgingLine line2;
        private BridgingLine line3;
        
        private final float coordsA[] = { 0f, 0f };
        private final float coordsB[] = { 0f, 0f };
        
        private final Paint linePaint;
        /**
         * @param context
         */
        public MyView(Context context) {
            super(context);
            boolean rounded = false;
            linePaint = new Paint(SUBPIXEL_TEXT_FLAG | ANTI_ALIAS_FLAG);
            linePaint.setStrokeCap(Paint.Cap.BUTT);
            linePaint.setColor(BLACK);
            linePaint.setStyle(STROKE);
            float strokeWidthPixel = STROKE_WIDTH_DP * density;
            linePaint.setStrokeWidth(strokeWidthPixel);
            init();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.WHITE);
            canvas.save();

            Matrix matrix = new Matrix();
            matrix.postScale(5, 5);
            canvas.concat(matrix);
            
            line1.draw(canvas);
            line2.draw(canvas);
            line3.draw(canvas);
            canvas.restore();
        }
        
        
        private void init(){
            line1 = new BridgingLine(initPathTop1(), initPathTop2());
            line2 = new BridgingLine(initPathMiddle1(), initPathMiddle2());
            line3 = new BridgingLine(initPathBottom1(), initPathBottom2());
        }
        
        
        private JoinedPath initPathTop1(){
            Path first = new Path();
            first.moveTo(5.042f, 20f);
            first.rCubicTo(8.125f, -16.317f, 39.753f, -27.851f, 55.49f, -2.765f);
            
            
            Path second = new Path();
            second.moveTo(60.531f, 17.235f);
            second.rCubicTo(11.301f, 18.015f, -3.699f, 46.083f, -23.725f, 43.456f);
            
            scalePath(first, density);
            scalePath(second, density);
            return new JoinedPath(first,second);
        }
        
        private JoinedPath initPathTop2(){
            Path first = new Path();
            first.moveTo(64.959f, 20f);
            first.rCubicTo(4.457f, 16.75f, 1.512f, 37.982f, -22.557f, 42.699f);
            Path second = new Path();
            second.moveTo(42.402f, 62.699f);
            second.cubicTo(18.333f, 67.418f, 8.807f, 45.646f, 8.807f, 32.823f);
            
            scalePath(first, density);
            scalePath(second, density);
            return new JoinedPath(first,second);
        }
        
        private JoinedPath initPathMiddle1(){
            Path first = new Path();
            first.moveTo(5.042f, 35f);
            first.cubicTo(5.042f, 20.333f, 18.625f, 6.791f, 35f, 6.791f);
            
            
            Path second = new Path();
            second.moveTo(35f, 6.791f);
            second.rCubicTo(16.083f, 0f, 26.853f, 16.702f, 26.853f, 28.209f);
            
            scalePath(first, density);
            scalePath(second, density);
            return new JoinedPath(first,second);
        }
        
        private JoinedPath initPathMiddle2(){
            Path first = new Path();
            first.moveTo(64.959f, 35f);
            first.rCubicTo(0f, 10.926f, -8.709f, 26.416f, -29.958f, 26.416f);
            Path second = new Path();
            second.moveTo(35f, 61.416f);
            second.rCubicTo(-7.5f, 0f, -23.946f, -8.211f, -23.946f, -26.416f);
            
            scalePath(first, density);
            scalePath(second, density);
            return new JoinedPath(first,second);
        }
        
        
        private JoinedPath initPathBottom1(){
            Path first = new Path();
            first.moveTo(5.042f, 50f);
            first.cubicTo(2.5f, 43.312f, 0.013f, 26.546f, 9.475f, 17.346f);
            
            
            Path second = new Path();
            second.moveTo(9.475f, 17.346f);
            second.rCubicTo(9.462f, -9.2f, 24.188f, -10.353f, 27.326f, -8.245f);
            
            scalePath(first, density);
            scalePath(second, density);
            return new JoinedPath(first,second);
        }
        
        private JoinedPath initPathBottom2(){
            Path first = new Path();
            first.moveTo(64.959f, 50f);
            first.rCubicTo(-7.021f, 10.08f, -20.584f, 19.699f, -37.361f, 12.74f);
            Path second = new Path();
            second.moveTo(27.598f, 62.699f);
            second.rCubicTo(-15.723f, -6.521f, -18.8f, -23.543f, -18.8f, -25.642f);
            
            scalePath(first, density);
            scalePath(second, density);
            return new JoinedPath(first,second);
        }
        

        public void setParameter(float parameter) {
            this.parameter = parameter;
            this.invalidate();
        }
        
        /** Draws a line between two {@link JoinedPath}s at distance {@code parameter} along each path. */
        private class BridgingLine {

          private final JoinedPath pathA;
          private final JoinedPath pathB;

          private BridgingLine(JoinedPath pathA, JoinedPath pathB) {
            this.pathA = pathA;
            this.pathB = pathB;
          }

          /**
           * Draw a line between the points defined on the paths backing {@code measureA} and
           * {@code measureB} at the current parameter.
           */
          private void draw(Canvas canvas) {
            pathA.getPointOnLine(parameter, coordsA);
            pathB.getPointOnLine(parameter, coordsB);
          
            canvas.drawLine(coordsA[0], coordsA[1], coordsB[0], coordsB[1], linePaint);
          }

        }
    }
    
    
    
    /**
     * Joins two {@link Path}s as if they were one where the first 50% of the path is {@code
     * PathFirst} and the second 50% of the path is {@code pathSecond}.
     */
    private static class JoinedPath {

      private final PathMeasure measureFirst;
      private final PathMeasure measureSecond;
      private final float lengthFirst;
      private final float lengthSecond;

      private JoinedPath(Path pathFirst, Path pathSecond) {
        measureFirst = new PathMeasure(pathFirst, false);
        measureSecond = new PathMeasure(pathSecond, false);
        lengthFirst = measureFirst.getLength();
        lengthSecond = measureSecond.getLength();
      }

      /**
       * Returns a point on this curve at the given {@code parameter}.
       * For {@code parameter} values less than .5f, the first path will drive the point.
       * For {@code parameter} values greater than .5f, the second path will drive the point.
       * For {@code parameter} equal to .5f, the point will be the point where the two
       * internal paths connect.
       */
      private void getPointOnLine(float parameter, float[] coords) {
        if (parameter <= .5f) {
          parameter *= 2;
          measureFirst.getPosTan(lengthFirst * parameter, coords, null);
        } else {
          parameter -= .5f;
          parameter *= 2;
          measureSecond.getPosTan(lengthSecond * parameter, coords, null);
        }
      }
    }
    
    /**
     * 缩放path不然画出来的箭头不完美
     * Scales the paths to the given screen density. If the density matches the
     * {@link DrawerArrowDrawable#PATH_GEN_DENSITY}, no scaling needs to be done.
     */
    private static void scalePath(Path path, float density) {
      if (density == PATH_GEN_DENSITY) return;
      Matrix scaleMatrix = new Matrix();
      scaleMatrix.setScale(density / PATH_GEN_DENSITY, density / PATH_GEN_DENSITY, 0, 0);
      path.transform(scaleMatrix);
    }
}
