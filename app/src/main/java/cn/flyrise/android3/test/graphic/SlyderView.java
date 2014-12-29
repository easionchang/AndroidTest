package cn.flyrise.android3.test.graphic;

import android.app.Activity;
import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.EmbossMaskFilter;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class SlyderView extends View{

    public SlyderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public SlyderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    public SlyderView(Context context) {
        super(context);
        init(context);
    }

    /**
     * ��Ļ���
     */
    private int screenW;
    /**
     * ��Ļ�ĸ߶�
     */
    private int screenH;
    /**
     * �ָ�Ķ���
     */
    private int [] drgrees = {20,50,40,90,70,40,50};
    /***
     * �ָ������
     */
    private String [] strs = {"level1","level2","level3","level4","level5","level6","level7"};
    /**
     * �ָ����ɫ
     */
    private int [] colos = new int[] { 0xfed9c960, 0xfe57c8c8, 0xfe9fe558, 0xfef6b000, 0xfef46212, 0xfecf2911, 0xfe9d3011 };
    /**
     * ����
     */
    private Paint paint;
    /**
     * ���ֵĴ�С
     */
    private float textSize  = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 15, getResources().getDisplayMetrics());
    /**
     * ���ֵ���ɫ
     */
    private int textcolor = Color.WHITE;
    /**
     * ԰�İ뾶
     */
    private float radius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 100, getResources().getDisplayMetrics());
    /**
     * �����ֵľ���
     */
    private float textdis = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 80, getResources().getDisplayMetrics());
    /**
     * ����ͷ�Ĵ�С
     */
    private float roketSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics());
    
    private float initDegress = 0;
    /**
     * Բ��
     */
    private float centerX;
    /**
     * Բ��
     */
    private float centerY;
    /**
     * �����Ե
     */
    private MaskFilter filter = new EmbossMaskFilter(new float[] { 1, 1, 1 },
            0.4f, 6, 3.5f);  
    
    private MaskFilter outerFilter = new BlurMaskFilter(10, BlurMaskFilter.Blur.OUTER);
    
    private MaskFilter innerFilter = new BlurMaskFilter(10, BlurMaskFilter.Blur.INNER);
    @SuppressWarnings("deprecation")
    private void init(Context context){
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Style.FILL);
        paint.setColor(Color.WHITE);
        screenW = ((Activity)context).getWindowManager().getDefaultDisplay().getWidth();
        screenH = ((Activity)context).getWindowManager().getDefaultDisplay().getHeight();
        
        int[] colores = new int[3];
        colores[0] = Color.rgb(0xfF, 0x99, 0x00);
        colores[1] = Color.rgb(0xff, 0xff, 0x00);
        colores[2] = Color.rgb(0xff, 0x99, 0x00);
        float[] positions = new float[3];
        positions[0] = 0.0f;
        positions[1] = 0.5f;
        positions[2] = 1.0f;
        
        gradient = new RadialGradient(centerX, centerY, radius/5, colores, positions, TileMode.CLAMP);
        
    }
    /**
     * �������Ǽ�ͷ
     */
    private Path path = new Path(); 
    /**
     * ���ƾ��ο�
     */
    private RectF oval;
    
    /**
     * ��Բ����Ӱ����
     */
    private ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(new float[]{
            1,0,0,0,0,
            0,1,0,0,0,
            0,0,1,0,0,
            0,0,0,-1,255
    });
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        centerX = screenW/2;
        centerY = screenH/2;
        oval = new RectF(centerX-radius,centerY-radius,centerX+radius,centerY+radius);
        float start = 0;
        
        paint.setColor(Color.rgb(0xdd, 0xdd, 0xdd));
        paint.setAlpha(127);
        canvas.drawCircle(centerX, centerY, radius+10, paint);
        paint.setAlpha(255);
        
        //������
        paint.setAntiAlias(true);
        for(int i=0;i<drgrees.length;i++){
            float sweepAngle = drgrees[i];
            float startAngle = start;
            paint.setColor(colos[i%colos.length]);
            canvas.drawArc(oval, startAngle, sweepAngle, true, paint);
            start += drgrees[i];
        }
        
        //������
        paint.setColor(textcolor);
        paint.setAntiAlias(true);
        paint.setTextSize(textSize);
        paint.setTextAlign(Paint.Align.RIGHT);
        start = 0;
        for(int i=0;i<drgrees.length;i++){
            canvas.save();
            canvas.rotate(start+drgrees[i]/2, centerX, centerY);
            canvas.drawText(strs[i], centerX+textdis, centerY, paint);
            canvas.restore();
            start += drgrees[i];
        }
        int saveCount = canvas.save();

        //���������Ч��
        paint.setColorFilter(colorFilter);
        canvas.saveLayer(oval,paint,Canvas.ALL_SAVE_FLAG);
        paint.setColorFilter(null);
        canvas.drawARGB(255, 0, 0, 0);
        paint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
        canvas.drawCircle(centerX, centerY, radius, paint);
        paint.setXfermode(null);
        paint.setMaskFilter(innerFilter);
        paint.setColor(Color.argb(0xff, 0, 0, 0));
        canvas.drawCircle(centerX, centerY, radius, paint);
        paint.setMaskFilter(null);
        canvas.restoreToCount(saveCount);
        
        //����Բ����԰Ч��
        canvas.save();
        paint.setColor(Color.argb(0xff, 0, 0, 0));
        paint.setAntiAlias(true);
        paint.setMaskFilter(outerFilter);
        canvas.rotate(initDegress, centerX, centerY);
        canvas.drawCircle(centerX, centerY, radius/3, paint);
        paint.setMaskFilter(null);
        paint.setColor(Color.WHITE);
        canvas.drawCircle(centerX, centerY, radius/3, paint);
        
        //�������͵��ӵ���ͷ
        path.moveTo(centerX-radius/3, centerY);
        path.lineTo(centerX, centerY-radius/3-roketSize);
        path.lineTo(centerX+radius/3, centerY);
        path.close();
        canvas.drawPath(path, paint);
        canvas.restore();

        paint.setMaskFilter(filter);
        paint.setColor(Color.GREEN);
        paint.setShader(gradient);
        canvas.drawCircle(centerX, centerY, radius/5, paint);
        paint.setMaskFilter(null);
        paint.setShader(null);
        //�ػ�������ǵ�ָ��ﵽ������Ч������ʵ��Ŀ�пɲ��������õģ�Ч��̫�����ˣ����View�ö�����ɹ�����������
        if(isRunning){
            if(initDegress>=360){
                initDegress = 0;
            }
            initDegress +=4;
            invalidate();
        }
        if(isStoping){
            if(initDegress<=360){
                initDegress+=4;
                invalidate();
            }else{
                if(initDegress-360<stop_degress){
                    initDegress+=2;
                    invalidate();
                }
            }
        }
        
    }
    
    private boolean isRunning = false;
    
    private boolean isStoping = false;
    
    private int stop_degress =90;

    /**
     * ����
     */
    private RadialGradient gradient;
    
    public void play(){
        isRunning = true;
        invalidate();
    }
    
    public void stop(int count){
        for(int i =0;i<=count;i++){
            if(i == count){
                stop_degress +=drgrees[i]/2; 
            }else{
                stop_degress +=drgrees[i]; 
            }
        }
        isStoping = true;
        isRunning = false;
        invalidate();
    }
    
}
