/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2012-9-15 上午09:44:32
 */
package cn.flyrise.android3.test.list.view;

import cn.flyrise.android3.test.R;
import cn.flyrise.android3.test.list.DragSwitchActivity;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemLongClickListener;

import java.util.Map;

/**
 * 类功能描述：</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * 修改时间：</br>
 * 修改备注：</br>
 */
public class DragListView extends ListView{

    boolean longClick = false;
    String key;
    String value;
    int  position;
    
    /**
     * @param context
     */
    public DragListView(Context context) {
        super(context);
       // setOnItemLongClickListener(this);
       
    }
    
    
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        return super.onInterceptTouchEvent(ev);
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        return super.onTouchEvent(ev);
    }


}
