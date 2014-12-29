/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2014-11-5 上午10:53:56
 */
package cn.flyrise.android3.test.view.content;

import cn.flyrise.android3.test.R;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
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
public class DrawerMenuFragment extends Fragment{
    
    
    @Override
    public View onCreateView(LayoutInflater inflater,
           ViewGroup container,Bundle savedInstanceState) {
//        ListView menuList = new ListView(this.getActivity());
//        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
//        menuList.setLayoutParams(lp);
//        menuList.setBackgroundColor(Color.WHITE);
        List<String> data = new ArrayList<String>();
        data.add("下载管理");
        data.add("清除缓存");
        data.add("反馈意见");
        data.add("关于我们");
        data.add("观看帮助");
//        menuList.setAdapter(new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, data));
//        return menuList; 
        
        
        
        View menuView = inflater.inflate(R.layout.draw_left_menu, container, false);
        ListView leftMenuLst = (ListView)menuView.findViewById(R.id.left_menu_lst);
        leftMenuLst.setAdapter(new MenuItemAdapter(data));
        return menuView;
    }
    
    
    class MenuItemAdapter extends BaseAdapter{
        public List<String> menuItemData;
        
        public MenuItemAdapter(List<String> data){
            menuItemData = data;
        }

        @Override
        public int getCount() {
            return menuItemData.size();
        }

        @Override
        public Object getItem(int position) {
            return menuItemData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.draw_left_menu_item, parent,false);
                //int height = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, parent.getResources().getDisplayMetrics());
                //LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT,height);
                //myTv.setTextColor(Color.rgb(70, 132, 215));
                //myTv.setTextSize(20);
                //myTv.setGravity(Gravity.CENTER);
                //myTv.setLayoutParams(lp);
                //convertView = myTv;
            }
            TextView titleTv = (TextView)convertView.findViewById(R.id.menu_title_tv);
            titleTv.setText(menuItemData.get(position));
            
            return convertView;
        }
    }

}
