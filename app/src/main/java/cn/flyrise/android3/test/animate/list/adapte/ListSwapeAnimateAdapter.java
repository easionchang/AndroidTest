/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-11-7 下午3:05:22
 */
package cn.flyrise.android3.test.animate.list.adapte;


import cn.flyrise.android3.test.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * 类功能描述：</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * 修改时间：</br>
 * 修改备注：</br>
 */
public class ListSwapeAnimateAdapter extends BaseAdapter{
    private Context mContext;
    List<Data> data = new ArrayList<Data>();
    ListView view;
    boolean consumed = false;
    int direction = 0;//默认是0 1--水平滑动  -1--垂直移动
    float x=0;
    float y=0;
    ViewConfiguration vc;
    int mSlop;
    boolean mSwiping;

    public ListSwapeAnimateAdapter(Context context,List<Data> data,ListView view){
        mContext = context;
        this.data = data;
        this.view = view;
        vc = ViewConfiguration.get(context);
        mSlop = vc.getScaledTouchSlop();
    }
    
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.e("Test", "===========>getView()");
//        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_1, null);
            ViewHolder viewTag = new ViewHolder();
            TextView textView = (TextView)convertView.findViewById(R.id.key);
            final Button btn = (Button)convertView.findViewById(R.id.btn);
            btn.setOnTouchListener(new OnTouchListener() {
                
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    Toast.makeText(mContext, "<<<<<onTouch", Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
            btn.setOnClickListener(new OnClickListener() {
                
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "<<<<<", Toast.LENGTH_SHORT).show();
                    
                }
            });
            viewTag.textView = textView;
            convertView.setTag(viewTag);
            convertView.setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    TextView tv = (TextView)v.findViewById(R.id.key);
                    switch(event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            x = event.getRawX();
                            y = event.getRawY();
                            Log.e("Test", "Action==ACTION_DOWN  direction=="+direction+" consumed=="+consumed+" x="+x+" y="+y);
                            consumed = true;
                            break;
                        case MotionEvent.ACTION_MOVE:
                            Log.e("Test", "Action==ACTION_MOVE event.getX()="+event.getX()+" event.getY()="+event.getY());
                            float deltaX = event.getRawX() - x;
                            float deltaY = event.getRawY() - y;
                            if(Math.abs(deltaX) > mSlop && Math.abs(deltaX) > Math.abs(deltaY)){
                                mSwiping = true;
                                view.requestDisallowInterceptTouchEvent(true);
                                consumed = true;
                                //Log.e("Test", "===========true");
                            }
                            
                            if (mSwiping) {
                                tv.setX(deltaX);
                                btn.setVisibility(View.VISIBLE);
                                return true;
                            }else{
                                return false;
                            }
                        case MotionEvent.ACTION_UP:
                            Log.e("Test", "Action==ACTION_UP  ");
                            tv.setX(0);
                            direction = 0;
                            consumed = false;
                            mSwiping = false;
                            btn.setVisibility(View.GONE);
                            view.requestDisallowInterceptTouchEvent(false);
                            break;
                    }
                    return consumed;
                }
            });
//        }
        //ViewHolder viewTag = (ViewHolder)convertView.getTag();
       textView.setText(data.get(position).getInfo());
        
       
        

        return convertView;
    }
    
    
    public void onAnimationEnd(){
        
    }

    class ViewHolder{
        TextView textView;
    }
    
    
    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

}
