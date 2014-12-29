/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2012-11-20 上午09:53:23
 */
package cn.flyrise.android3.test.expendlist;

import cn.flyrise.android3.test.R;

import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
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
public class ExpendlistWithSimpleAdapter extends Activity {
    
    private List<Map<String,String>> groupData = new ArrayList<Map<String,String>>();
    private List<List<Map<String,String>>> childData = new ArrayList<List<Map<String,String>>>();
        
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_expend_list);
        
        for(int i=0;i<2;i++){
            Map<String,String> map1 = new HashMap<String,String>();
            map1.put("text", "Group"+(i+1));
            groupData.add(map1);
            
            List<Map<String,String>> cdata = new ArrayList<Map<String,String>>();
            for(int j=0;j<2;j++){
                Map<String,String> map2 = new HashMap<String,String>();
                map2.put("text", "Child"+(i+1));
                cdata.add(map2);
            }
            childData.add(cdata);
        }
        
        
        ExpandableListView elist = (ExpandableListView)findViewById(R.id.expand_list);
        elist.setAdapter(new SimpleExpandableListAdapter(this, groupData, R.layout.simple_expand_group,
                         new String[]{"text"}, new int[]{R.id.tv}, childData, R.layout.simple_expand_group,
                         new String[]{"text"}, new int[]{R.id.tv}));
        
    }

}
