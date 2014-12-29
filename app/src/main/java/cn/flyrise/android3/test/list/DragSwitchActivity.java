/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2012-9-7 下午02:28:00
 */
package cn.flyrise.android3.test.list;

import cn.flyrise.android3.test.R;
import cn.flyrise.android3.test.R.id;
import cn.flyrise.android3.test.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemLongClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类功能描述：</br>
 * ListView拖拽功能
 * @author zms
 * @version 1.0
 * </p>
 * 修改时间：</br>
 * 修改备注：</br>
 */
public class DragSwitchActivity extends Activity{
    
    List<Map<String,String>> data;
    SimpleAdapter adapter;
    LinearLayout floatView;
    boolean longClick = false;
    String key;
    String value;
    int  position;
    ListView lstv;
    
    private int y = 0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_main);
        
        floatView = (LinearLayout)findViewById(R.id.float_view);
        floatView.setVisibility(View.INVISIBLE);
       
        
        lstv = (ListView)findViewById(R.id.list);
        
        
        String[] from = new String[]{"key","value"};
        int[] to = new int[]{R.id.key,R.id.value};
        data = getData();
        adapter = new SimpleAdapter(this,data , R.layout.list_item, from, to);
        
        lstv.setAdapter(adapter);
        
        lstv.setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                    int position, long id) {
//                for(int i=0;i<lstv.getChildCount();i++){
//                    Log.e("Test",i+".top==" + lstv.getChildAt(i).getTop());
//                }
//                Log.e("Test", "onItemLongClick>>>>>>position="+position+">>>>viewPosition="+lstv.getFirstVisiblePosition()+">>>>>"+lstv.getLastVisiblePosition()+">>>>>>>>");
               
                
//                floatView.setVisibility(View.VISIBLE);
//                AbsoluteLayout.LayoutParams layoutParams = (AbsoluteLayout.LayoutParams)floatView.getLayoutParams();
//                layoutParams.x = 0;
//                layoutParams.y=100;
                
                
                setDataNull(position);
                DragSwitchActivity.this.position = position;
                longClick = true;
                Log.e("DragSwitchActivity", "lstv.getChildCount()=="+lstv.getChildCount());
                return true;
            }
            
        });
        
    
        
        
        lstv.setOnTouchListener(new OnTouchListener() {
            
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()){
                    case MotionEvent.ACTION_MOVE:
                        if(longClick){
                            floatView.setVisibility(View.VISIBLE);
                            TextView keyTv = (TextView)floatView.findViewById(R.id.key);
                            keyTv.setText(key);
                            TextView valueTv = (TextView)floatView.findViewById(R.id.value);
                            valueTv.setText(value);
                            floatView.layout(floatView.getLeft(), (int)event.getY(), floatView.getRight(), (int)event.getY() + floatView.getHeight());    
                        
                            View nextView = lstv.getChildAt(position - lstv.getFirstVisiblePosition() + 1);
                            if(nextView != null){
                                int top = nextView.getTop();
                                int bottom = nextView.getBottom();
                               
                                if(event.getY() >= top && event.getY() <= bottom){
                                    Log.e("Test", "position==>"+position+"  top="+top+"   bottom="+bottom+" getY="+event.getY());
                                    position += 1; 
                                    changeData(position,false);
                                }
                            }  
                            
                            
                            View preView = lstv.getChildAt(position - lstv.getFirstVisiblePosition() - 1);
                            if(preView != null){
                                int top = preView.getTop();
                                int bottom = preView.getBottom();
                                if(event.getY() >= top && event.getY() <= bottom){
                                    position -= 1; 
                                    changeData(position,true);
                                    
                                }
                            }
                            
                            if(event.getY() <= 120 && (y - event.getY()) > 0){
                                //lstv.scrollBy(0, -44);
                                
                                lstv.setSelectionFromTop(lstv.getFirstVisiblePosition(), lstv.getChildAt(0).getTop() + 8);
                                
                            }
                            
                            if(event.getY() >= lstv.getBottom()-120 && (y - event.getY()) < 0){
                                //lstv.scrollBy(0, -44);
                                Log.e("Test", "lstv.getChildAt(lstv.getChildCount())=="+lstv.getChildAt(lstv.getChildCount())+"  lstv.getChildCount()=="+lstv.getChildCount()) ;
                                lstv.setSelectionFromTop(lstv.getFirstVisiblePosition(), lstv.getChildAt(0).getTop() - 8);
                                
                            }
                            y = (int)event.getY();
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if(longClick){
                            longClick = false;
                            setData(position);
                        }
                        floatView.setVisibility(View.INVISIBLE);
                        break;
                    case MotionEvent.ACTION_DOWN:
                        y = (int)event.getY();
                        break;
                }
                return false;
            }
        });
       
   }
    
    private List<Map<String,String>> getData(){
        List<Map<String,String>> data = new ArrayList<Map<String,String>>();
        
        for(int i=1;i<54;i++){
            Map<String,String> map = new HashMap<String,String>();
            if(i>=10){
                map.put("key", "2012-08-"+i);
            }else{
                map.put("key", "2012-08-0"+i);
            }
            map.put("value", "工作日志");
            data.add(map);
        }
        
        return data;
    }
    
    
    private void setDataNull(int position){
        Log.e("Test", "setDataNull>>>>>>>position="+position+">>>>>>>>>>>>>>>>");
        Map<String,String> itemData = data.get(position);
        key = itemData.get("key");
        value = itemData.get("value");
        itemData.put("key", "");
        itemData.put("value", "");
        adapter.notifyDataSetChanged();
    }
    
    private void setData(int position){
        Map<String,String> itemData = data.get(position);
        //key = itemData.get("key");
        //value = itemData.get("value");
        itemData.put("key", key);
        itemData.put("value", value);
        adapter.notifyDataSetChanged();
    }
    private void changeData(int position,boolean isUp){
        Log.e("Test", "position="+position+" isUp="+isUp);
        Map<String,String> itemData = data.get(position);
        String key = itemData.get("key");
        String value = itemData.get("value");
        itemData.put("key", "");
        itemData.put("value", "");
        
        if(isUp){
            Map<String,String> upData = data.get(position+1);
            upData.put("key", key);
            upData.put("value", value);
        }else{
            Map<String,String> downData = data.get(position-1);
            downData.put("key", key);
            downData.put("value", value); 
        }
        
        adapter.notifyDataSetChanged();
    }

}
