/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-12-18 上午10:01:44
 */
package cn.flyrise.android3.test.list;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.HeaderViewListAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
public class TestHeaderViewFootView extends ListActivity{
    //0 初始状态，1 正在刷新
    int status = 0;
    
    List<String> data = new ArrayList<String>();
    
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case 1:
                    adapter.notifyDataSetChanged();
                    status = 0;
                    break;
            }
        }
        
    };
    
    private Runnable run = new Runnable(){
        @Override
        public void run() {
            addData();
            handler.sendEmptyMessage(1);
        }
        
    };
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ListView lstView = getListView();
        lstView.setOnScrollListener(new OnScrollListener() {
            
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }
            
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                    int visibleItemCount, int totalItemCount) {
                Log.e("Test", "totalItemCount=="+totalItemCount);
                if(firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0){
                    Log.e("Test", "refresh>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                    if(status == 0){
                        handler.postDelayed(run, 3000);
                        status = 1;
                    }
                    
                }
            }
        });
        
        TextView tv = new TextView(this);
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(lp);
        tv.setGravity(Gravity.CENTER);
        tv.setText("正在加载更多...");
        
        lstView.addFooterView(tv);
        addData();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        lstView.setAdapter(adapter);
    }
    
    
    private void addData(){
        int length = data.size();
        for(int i=0;i<20;i++){
            data.add("数据"+(length+i));
        }
    }
}
