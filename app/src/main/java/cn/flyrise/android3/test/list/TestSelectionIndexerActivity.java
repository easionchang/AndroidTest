/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-12-6 下午1:39:02
 */
package cn.flyrise.android3.test.list;

import cn.flyrise.android3.test.R;
import cn.flyrise.android3.test.list.adpter.SelectionIndexerAdapter;
import cn.flyrise.android3.test.list.view.SideBar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * 类功能描述：</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * 修改时间：</br>
 * 修改备注：</br>
 */
public class TestSelectionIndexerActivity extends Activity{
    
    
    
    /** Called when the activity is first created. */  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.list_selection_indexer);  
        ListView list = (ListView) findViewById(R.id.myListView);  
        ArrayList<String> stringList = InitListViewData();  
        SelectionIndexerAdapter adapter = new SelectionIndexerAdapter(this, stringList);  
        list.setAdapter(adapter);  
        SideBar indexBar = (SideBar) findViewById(R.id.sideBar);  
        indexBar.setListView(list,adapter);  
        
        
        
    }  
    private ArrayList<String> InitListViewData() {  
        ArrayList<String> stringList = new ArrayList<String>();  
        stringList.add("aback");  
        stringList.add("abash");  
        stringList.add("abbey");  
        stringList.add("abhor");  
        stringList.add("abide");  
        stringList.add("abuse");  
        stringList.add("candidate");  
        stringList.add("capture");  
        stringList.add("careful");  
        stringList.add("catch");  
        stringList.add("cause");  
        stringList.add("celebrate");  
        stringList.add("forever");  
        stringList.add("fable");  
        stringList.add("fidelity");  
        stringList.add("fox");  
        stringList.add("funny");  
        stringList.add("fail");  
        stringList.add("jail");  
        stringList.add("jade");  
        stringList.add("jailor");  
        stringList.add("january");  
        stringList.add("jasmine");  
        stringList.add("jazz");  
        stringList.add("zero");  
        stringList.add("zoo");  
        stringList.add("zeus");  
        stringList.add("zebra");  
        stringList.add("zest");  
        stringList.add("zing");  
        return stringList;  
    }  
}
