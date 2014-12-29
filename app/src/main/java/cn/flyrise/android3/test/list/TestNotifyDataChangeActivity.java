/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-1-16 下午1:52:50
 */
package cn.flyrise.android3.test.list;


import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
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
public class TestNotifyDataChangeActivity extends ListActivity{
    
    List<String> data;
    int i = 10;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        //setContentView(R.layout.list_main);
        data = getData(i);
        adapter = new MyAdapter(data,this);
        getListView().setAdapter(adapter);
       
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "Add");
        menu.add(0, 1, 1, "Update");
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == 0){
            adapter.add(i+"");
            i++;
            return true;
        }else if(item.getItemId() == 1){
            //data = getData2(10);
            adapter.setData(getData2(10));
            adapter.notifyDataSetInvalidated();
        }
        return super.onOptionsItemSelected(item);
    }
    
    private List<String> getData(int count){
        List<String> data = new ArrayList<String>();
        for(int i=0;i<count;i++){
            data.add(i+"");
        }
        return data;
    }
    
    private List<String> getData2(int count){
        List<String> data = new ArrayList<String>();
        for(int i=0;i<count;i++){
            data.add("数据："+i);
        }
        return data;
    }
    
    
    class MyAdapter extends BaseAdapter {
        
        List<String> data;
        public List<String> getData() {
            return data;
        }

        public void setData(List<String> data) {
            this.data = data;
        }

        Context mcontext = null;
        
        public MyAdapter(List<String> data, Context mcontext){
            this.data = data;
            this.mcontext = mcontext;
        }
        
        public void add(String txt){
            data.add(txt);
            this.notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return data.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return data.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return arg0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            
            TextView  view =  (TextView)LayoutInflater.from(mcontext).inflate(android.R.layout.simple_list_item_1, null);
            view.setText(data.get(position));
            return view;
        }
        
    }

}
