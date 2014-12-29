/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-12-6 下午1:42:40
 */
package cn.flyrise.android3.test.list.view;

import cn.flyrise.android3.test.ApiDemos;
import cn.flyrise.android3.test.R;
import cn.flyrise.android3.test.list.TestSelectionIndexerActivity;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;

/**
 * 类功能描述：</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * 修改时间：</br>
 * 修改备注：</br>
 */
public class SideBar extends View {  
    private Context mContext;
    private char[] l;  
    private SectionIndexer sectionIndexter = null;  
    private ListView list;  
    float textSize;
    int charHeigth ;
    WindowManager mWindowManager;
    TextView textView;
    private boolean ontouch = false;
    int touchIndex = -1;
    Handler mHandler = new Handler();
    public SideBar(Context context) {  
        super(context);  
        init();  
    }  
    public SideBar(Context context, AttributeSet attrs) {  
        super(context, attrs);  
        mContext = context;
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.SliedeBar,
                0, 0);
        textSize = a.getDimension(R.styleable.SliedeBar_textSize, 20);
       
        mWindowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        init();  
    }  

    public SideBar(Context context, AttributeSet attrs, int defStyle) {  
        super(context, attrs, defStyle);  
        init();  
    }  
    
    private void init() {  
        l = new char[] {'#', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',  
                'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };  
        setBackgroundColor(0x44FFFFFF);  
        Log.e("Test", ">>>>>>>>>>TestSelectionIndexerActivity.screenHeight=="+ApiDemos.screenHeight+"   l.length="+l.length);
        
        addTip();
    }  
    public void setListView(ListView _list,SectionIndexer sectionIndexter) {  
        list = _list;  
        this.sectionIndexter = sectionIndexter;  
        //list.setFastScrollEnabled(true);
       // list.setFastScrollAlwaysVisible(true);
    }  
    public boolean onTouchEvent(MotionEvent event) {  
        super.onTouchEvent(event);  

        int i = (int) event.getY();  
        int idx = i / charHeigth;  
        if (idx >= l.length) {  
            idx = l.length - 1;  
        } else if (idx < 0) {  
            idx = 0;  
        }  
        
        touchIndex = idx;
        if(event.getAction() == MotionEvent.ACTION_DOWN ){
            ontouch = true;
            
        }
        
        if(event.getAction() == MotionEvent.ACTION_UP ){
            ontouch = false;
            touchIndex = -1;
            //showTip(false);
        }
        showTip(true);
        invalidate();
        
        mHandler.removeCallbacks(removebale);
        mHandler.postDelayed(removebale, 2000);
        
        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {  
            if (sectionIndexter == null) {  
                sectionIndexter = (SectionIndexer) list.getAdapter();  
            } 
            textView.setText(l[idx]+"");
            //int position = sectionIndexter.getPositionForSection(l[idx]);  
            int position = sectionIndexter.getPositionForSection(idx);  
            if (position == -1) {  
                return true;  
            }  
            list.setSelection(position);  
        }  
        return true;  
    }  
    
    Runnable removebale = new Runnable(){

        @Override
        public void run() {
            showTip(false);
        }
        
    };
    
    protected void onDraw(Canvas canvas) {
        charHeigth = getMeasuredHeight()/l.length;
        Paint paint = new Paint();  
        paint.setColor(0xFFA6A9AA);  
        paint.setTextSize(textSize);  
        paint.setTextAlign(Paint.Align.CENTER);  
        float widthCenter = getMeasuredWidth() / 2;  
        for (int i = 0; i < l.length; i++) {  
            //canvas.drawText(String.valueOf(l[i]), widthCenter, m_nItemHeight + (i * m_nItemHeight), paint); 
            if(touchIndex == i){
                Paint paint2 = new Paint();  
                paint2.setColor(0xFFFF0000);  
                paint2.setTextSize(textSize);  
                paint2.setTextAlign(Paint.Align.CENTER);  
                canvas.drawText(String.valueOf(l[i]), widthCenter, charHeigth + (i * charHeigth), paint2); 
            }else{
                canvas.drawText(String.valueOf(l[i]), widthCenter, charHeigth + (i * charHeigth), paint); 
            }
        }
        
        if(ontouch){
            setBackgroundColor(0xBBA6A9AA);
        }else{
            setBackgroundColor(0x44FFFFFF); 
        }
    }  
    
    private void addTip(){
        WindowManager.LayoutParams wlp = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        textView = (TextView)LayoutInflater.from(mContext).inflate(R.layout.list_position, null);
        textView.setText("T");
        textView.setVisibility(View.INVISIBLE);
       
        mWindowManager.addView(textView, wlp);
    }
    
    
    
    private void showTip(boolean showOrHide){
        if(!showOrHide){
            textView.setVisibility(View.INVISIBLE);
        }else{
            textView.setVisibility(View.VISIBLE);
        }
    }
}  