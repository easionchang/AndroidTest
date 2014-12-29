/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2012-11-20 上午09:53:23
 */
package cn.flyrise.android3.test.expendlist;

import cn.flyrise.android3.test.R;

import android.app.Activity;
import android.os.Bundle;
import android.provider.SyncStateContract.Constants;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
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
public class SimpleExpendlist extends Activity {
    
    private String[] groupList = {"Group1","Group2"};
    private String[][] childList = {{"111","222"},{"333","444"}};
    private Map<String,List<String>> data = new HashMap<String,List<String>>();
    private ExpandableListView elist;
        
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_expend_list);
        
       
        
        elist = (ExpandableListView)findViewById(R.id.expand_list);
        registerForContextMenu(elist);
        
        //elist.setGroupIndicator(getResources().getDrawable(R.drawable.icon));
        
        //设置为空
        //elist.setGroupIndicator(null);
        
        elist.setOnChildClickListener(new OnChildClickListener() {
            public boolean onChildClick(ExpandableListView parent, View v,
                    int groupPosition, int childPosition, long id) {
                //Toast.makeText(SimpleExpendlist.this, childList[groupPosition][childPosition], Toast.LENGTH_LONG).show();
                
                //获取点击的元素属于列表中第几行（关闭了就不算了）
                Toast.makeText(SimpleExpendlist.this, "groupPosition="+groupPosition+"  childPosition="+childPosition+"  "+elist.getFlatListPosition(elist.getPackedPositionForChild(groupPosition, childPosition))+"", Toast.LENGTH_LONG).show();;
                
                
                // elist.setSelectedGroup(1);  //不知道什么意思???????????
                //Toast.makeText(SimpleExpendlist.this, elist.getSelectedId()+"", Toast.LENGTH_LONG).show();;
                return false;
                
               
            }
        });
        
        elist.setOnGroupExpandListener(new OnGroupExpandListener() {
            public void onGroupExpand(int groupPosition) {
                for(int i=0;i<elist.getAdapter().getCount();i++){
                    if(i!=groupPosition){
                        elist.collapseGroup(i);
                    }
                }
                //获取列表中第四个位置上的一行属于哪一个Group
                //Toast.makeText(SimpleExpendlist.this, elist.getPackedPositionGroup(elist.getExpandableListPosition(3))+"", Toast.LENGTH_LONG).show();
                
                 
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
                convertView = LayoutInflater.from(SimpleExpendlist.this).inflate(R.layout.simple_expand_group, null);
                TextView tv = (TextView)convertView.findViewById(R.id.tv);
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
                convertView = LayoutInflater.from(SimpleExpendlist.this).inflate(R.layout.simple_expand_group, null);
                TextView tv = (TextView)convertView.findViewById(R.id.tv);
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
        
    }
    
    
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)  
    {  
        ExpandableListContextMenuInfo emenuInfo = (ExpandableListContextMenuInfo)menuInfo; 
        long pp = emenuInfo.packedPosition;
        String title = ((TextView) emenuInfo.targetView.findViewById(R.id.tv)).getText().toString();
        showExpandInfo(pp,title);
        menu.setHeaderTitle("菜单");  
        menu.add(0, 0, 0, "Action");  
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        ExpandableListContextMenuInfo menuInfo = (ExpandableListContextMenuInfo) item.getMenuInfo(); 
        long pp = menuInfo.packedPosition;
        
        String title = ((TextView)menuInfo.targetView.findViewById(R.id.tv)).getText().toString();
        showExpandInfo(pp,title);
        
        
        return super.onContextItemSelected(item);
    } 
    
    /**
     * 显示点击的是Group还是Child；并显示title
     * @param packedPosition
     * @param title
     */
    private void showExpandInfo(long packedPosition,String title){
        switch(elist.getPackedPositionType(packedPosition)){
            case ExpandableListView.PACKED_POSITION_TYPE_GROUP:
                Toast.makeText(SimpleExpendlist.this, "GROUP"+"="+title, Toast.LENGTH_SHORT).show();
                break;
            case ExpandableListView.PACKED_POSITION_TYPE_CHILD:
                Toast.makeText(SimpleExpendlist.this, "CHILD"+"="+title, Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(SimpleExpendlist.this, "NULL", Toast.LENGTH_SHORT).show();
                break;
        } 
    }


}
