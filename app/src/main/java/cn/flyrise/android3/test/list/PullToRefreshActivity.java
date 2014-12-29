/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-12-18 下午2:47:45
 */
package cn.flyrise.android3.test.list;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
public class PullToRefreshActivity extends ListActivity{

    private List<String> data = new ArrayList<String>();
    ListView lstView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lstView = getListView();
        addData();
        lstView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data));
        
        lstView.setOnScrollListener(new OnScrollListener() {
            
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                    int visibleItemCount, int totalItemCount) {
                
            }
        });
        
        lstView.setOnTouchListener(new OnTouchListener() {
            
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(lstView.getFirstVisiblePosition() == 0){
                    if(lstView.getChildAt(0).getTop()==0){
                        Log.e("Test", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>ding");
                    }
                }
                return false;
            }
        });
    }
    
    private void addData(){
        int length = data.size();
        for(int i=0;i<20;i++){
            data.add("数据"+(length+i));
        }
    }
}
