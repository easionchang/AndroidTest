/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2012-11-20 上午09:53:23
 */
package cn.flyrise.android3.test.expendlist;

import cn.flyrise.android3.test.R;
import cn.flyrise.android3.test.expendlist.CustomExpendListView.IGroupInfoChangedListener;

import android.app.Activity;
import android.os.Bundle;
import android.provider.SyncStateContract.Constants;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ExpandableListView.ExpandableListContextMenuInfo;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

import java.util.HashMap;
import java.util.List;
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
public class CustomExpendlistActivity extends Activity {
    
    private String TAG = "CustomExpendlist";
    
    private String[] groupList = {"Group1","Group2","Group3","Group4","Group5","Group6"};
    private String[][] childList = {{"1","2","3","4","5","6","7","8","9","10"},
                                    {"1","2","3","4","5","6","7","8","9","10"},
                                    {"1","2","3","4","5","6","7","8","9","10"},
                                    {"1","2","3","4","5","6","7","8","9","10"},
                                    {"1","2","3","4","5","6","7","8","9","10"},
                                    {"1","2","3","4","5","6","7","8","9","10"}};
    private CustomExpendListView elist;
    
    private int currGroup  = 0;
    
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_expend_list2);
        
        //tv = (TextView)findViewById(R.id.tv);
        
        elist = (CustomExpendListView)findViewById(R.id.expand_list);
        registerForContextMenu(elist);
        
        //elist.setGroupIndicator(getResources().getDrawable(R.drawable.icon));
        
        //设置为空
        elist.setGroupIndicator(null);
        
        View headeView = getLayoutInflater().inflate(R.layout.simple_expand_group,elist,false);
        final TextView tv = (TextView)headeView.findViewById(R.id.tv);
        elist.setHeaderView(headeView);
        

        elist.setListener(new IGroupInfoChangedListener() {
            
            @Override
            public void groupChanged(int groupIndex) {
                tv.setText(groupList[groupIndex]);
            }
        });
        
        
        elist.setAdapter(new BaseExpandableListAdapter() {
            
            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return true;
            }
            
            @Override
            public boolean hasStableIds() {
                // TODO Auto-generated method stub
                return false;
            }
            
            @Override
            public View getGroupView(int groupPosition, boolean isExpanded,
                    View convertView, ViewGroup parent) {
                TextView tv = null;
                if(convertView == null){
                    convertView = LayoutInflater.from(CustomExpendlistActivity.this).inflate(R.layout.simple_expand_group, null);
                    tv = (TextView)convertView.findViewById(R.id.tv);
                    ViewTag tag = new ViewTag();
                    tag.tv = tv;
                    convertView.setTag(tag);
                }else{
                    tv = ((ViewTag)convertView.getTag()).tv;
                }
                tv.setText(groupList[groupPosition]);
                return convertView;
            }
            
            @Override
            public long getGroupId(int groupPosition) {
                // TODO Auto-generated method stub
                return groupPosition;
            }
            
            @Override
            public int getGroupCount() {
                // TODO Auto-generated method stub
                return groupList.length;
            }
            
            @Override
            public Object getGroup(int groupPosition) {
                return groupList[groupPosition];
            }
            
            @Override
            public int getChildrenCount(int groupPosition) {
                return childList[groupPosition].length;
            }
            
            @Override
            public View getChildView(int groupPosition, int childPosition,
                    boolean isLastChild, View convertView, ViewGroup parent) {
                TextView tv = null;
                if(convertView == null){
                    convertView = LayoutInflater.from(CustomExpendlistActivity.this).inflate(R.layout.simple_expand_child, null);
                    tv = (TextView)convertView.findViewById(R.id.tv);
                    ViewTag tag = new ViewTag();
                    tag.tv = tv;
                    convertView.setTag(tag);
                }else{
                    tv = ((ViewTag)convertView.getTag()).tv;
                }
                
                tv.setText(childList[groupPosition][childPosition]);
                return convertView;
            }
            
            
            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
            }
            
            @Override
            public Object getChild(int groupPosition, int childPosition) {
                // TODO Auto-generated method stub
                return childList[groupPosition][childPosition];
            }
        });
        
        elist.expandGroup(0);
        elist.expandGroup(1);
        elist.expandGroup(2);
        elist.expandGroup(3);
        elist.expandGroup(4);
        elist.expandGroup(5);
        
    }   

    
    class ViewTag{
        public TextView tv;
    }
}
