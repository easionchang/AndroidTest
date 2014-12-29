/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2012-11-20 ����09:53:23
 */
package cn.flyrise.android3.test.expendlist;

import cn.flyrise.android3.test.R;

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
 * �๦��������</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * �޸�ʱ�䣺</br>
 * �޸ı�ע��</br>
 */
public class MyExpendlistActivity extends Activity {
    
    private String TAG = "CustomExpendlist";
    
    private String[] groupList = {"Group1","Group2","Group3","Group4","Group5","Group6"};
    private String[][] childList = {{"1","2","3","4","5","6","7","8","9","10"},
                                    {"1","2","3","4","5","6","7","8","9","10"},
                                    {"1","2","3","4","5","6","7","8","9","10"},
                                    {"1","2","3","4","5","6","7","8","9","10"},
                                    {"1","2","3","4","5","6","7","8","9","10"},
                                    {"1","2","3","4","5","6","7","8","9","10"}};
    private ExpandableListView elist;
    
    private int currGroup  = 0;
    
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_expend_list);
        
        tv = (TextView)findViewById(R.id.tv);
        
        elist = (ExpandableListView)findViewById(R.id.expand_list);
        registerForContextMenu(elist);
        
        //elist.setGroupIndicator(getResources().getDrawable(R.drawable.icon));
        
        //����Ϊ��
        elist.setGroupIndicator(null);
        
       
        
        


        
        
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
                    convertView = LayoutInflater.from(MyExpendlistActivity.this).inflate(R.layout.simple_expand_group, null);
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
                    convertView = LayoutInflater.from(MyExpendlistActivity.this).inflate(R.layout.simple_expand_child, null);
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
        
        
        elist.setOnScrollListener(new OnScrollListener() {
            
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {}
            
            
            public void onScroll(AbsListView view, int firstVisibleItem,
                    int visibleItemCount, int totalItemCount) {
                try {
                    srollFloatingView(firstVisibleItem,visibleItemCount);
                } catch (Exception e) {
                  Log.e(TAG, e.getMessage(),e);
                }
                
              
        }
        
        });
    }
    
    /**
     * ������Ҫ����������ʾ����
     * 1.���ȼ��㵱ǰӦ��ʾ������һ�����Group��
     * 2.�����ǰ������һ�����ڹ���������ʱ�̹���������
     * 3.�����һ���鲻�ڹ������͹̶�������
     */
    private void srollFloatingView(int firstVisibleItem,
            int visibleItemCount){
        int opGroupIndex = getOringinPointGroupIndex();
        if(opGroupIndex == -1){ //�����ǰ��ʾ�������-1�����账��
            return;
        }
        
        changeFloatingInfo(opGroupIndex); 
        
        int nextGroupIndex = currGroup + 1;
        boolean needScroll = isGroupInScrollArea(nextGroupIndex,firstVisibleItem, visibleItemCount);
        if(needScroll){
            scroll(getFlatListPositionForGroup(nextGroupIndex), firstVisibleItem);
        }else{
            fixFloatingView();
        }
    }
    
    /**
     * ����������Ϣ
     * ��ǰ������ʾ������һ�����
     * @param opGroupInde
     */
    private void changeFloatingInfo(int groupIndex){
        if(currGroup != groupIndex){
            currGroup = groupIndex;
            tv.setText(groupList[groupIndex]);
        }
    }
    
    /**
     * ���������̶���ͷ��
     */
    private void fixFloatingView(){
        if(tv.getTop() != 0){
            tv.layout(tv.getLeft(), 0, tv.getRight(), tv.getHeight());
        }
    }
    
    /**
     * 
     * ������Group������
     */
    private void scroll(int groupIndex,int firstVisibleItem){
        View v = elist.getChildAt(groupIndex - firstVisibleItem);
        tv.layout(tv.getLeft(), v.getTop() - tv.getHeight(), tv.getRight(), v.getTop());//��Ч�����⣿����������������
    }
    
    
    /**
     * �ж�ĳ������Ƿ��ڹ�����
     * ����ڹ���������������Ҫ�ƶ�
     * ������Ҫ�ƶ�
     * @param firstVisibleItem
     * @param visibleItemCount
     * @return
     */
    private boolean isGroupInScrollArea(int groupIndex,int firstVisibleItem,int visibleItemCount){
        int nextGroupIndex = getFlatListPositionForGroup(groupIndex);
        boolean nextGroupInScrollArea = isInScrollArea(nextGroupIndex, firstVisibleItem,visibleItemCount) ;
        if(nextGroupInScrollArea){
             return true;
        }else{
            return false;
        }
    }
    
    
    /**
     * �ж�ĳ��List�е�Item�Ƿ��ڹ�����
     * @param itemIndex
     * @param firstVisibleItem
     * @return
     */
    private boolean isInScrollArea(int itemIndex,int firstVisibleItem,int visibleItemCount){
        boolean itemVisiable = isVisiable(itemIndex, firstVisibleItem, visibleItemCount);
        if(!itemVisiable){  //�������Ŀ�����Ͳ��ɼ�����϶����ڹ�����
            return false;
        }
        
        View v = elist.getChildAt(itemIndex - firstVisibleItem);
        if(v.getTop() >= 0 && v.getTop() <= tv.getHeight()){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * �ж�ĳ��Item�Ƿ�������ʾ
     * @param listIndex
     * @return
     */
    private boolean isVisiable(int itemIndex,int firstVisibleItem,int visibleItemCount){
        if(itemIndex < visibleItemCount + firstVisibleItem && itemIndex >= firstVisibleItem){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * ��ȡGroup��ListView�е�λ��
     * @param groupIndex
     * @return
     */
    private int getFlatListPositionForGroup(int groupIndex){
        return elist.getFlatListPosition(ExpandableListView.getPackedPositionForGroup(groupIndex));
    }
    
    /**
     * �ҳ�ԭ�㣨0,0������Ŀ������һ�����
     * �����ĸ�������������͸���ʾ�ĸ�������ʾ
     * @return
     */
    private int getOringinPointGroupIndex(){
        int originItemIndex = elist.pointToPosition(0, 0);
        long packegeIndex = elist.getExpandableListPosition(originItemIndex);
        int groupIndex = ExpandableListView.getPackedPositionGroup(packegeIndex);
        return groupIndex;
    }
    
    class ViewTag{
        public TextView tv;
    }
}
