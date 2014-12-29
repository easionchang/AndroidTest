/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2012-12-13 ����11:32:34
 */
package cn.flyrise.android3.test.compnent;

import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.text.Selection;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.EditText;

/**
 * �๦��������</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * �޸�ʱ�䣺</br>
 * �޸ı�ע��</br>
 */
public class SelectAndCopyTextView extends EditText {    
    private int off; //�ַ�����ƫ��ֵ    
    
    public SelectAndCopyTextView(Context context) {    
        super(context);    
        initialize();    
    }    
    
    private void initialize() {    
        setGravity(Gravity.TOP);    
        setBackgroundColor(Color.WHITE);    
    }    
        
    @Override    
    protected void onCreateContextMenu(ContextMenu menu) {    
        //�����κδ���Ϊ����ֹ������ʱ�򵯳������Ĳ˵�    
    }    
        
    @Override    
    public boolean getDefaultEditable() {    
        return true;    
    }    
        
    @Override    
    public boolean onTouchEvent(MotionEvent event) {    
        int action = event.getAction();    
        Layout layout = getLayout();    
        int line = 0;    
        switch(action) {    
        case MotionEvent.ACTION_DOWN:    
            line = layout.getLineForVertical(getScrollY()+ (int)event.getY());            
            off = layout.getOffsetForHorizontal(line, (int)event.getX());    
            Selection.setSelection(getEditableText(), off);    
            break;    
        case MotionEvent.ACTION_MOVE:    
        case MotionEvent.ACTION_UP:    
            line = layout.getLineForVertical(getScrollY()+(int)event.getY());     
            int curOff = layout.getOffsetForHorizontal(line, (int)event.getX());                
            Selection.setSelection(getEditableText(), off, curOff);    
            break;    
        }    
        return true;    
    }    
}    