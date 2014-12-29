/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2012-9-24 下午02:49:20
 */
package cn.flyrise.android3.test.list;

import cn.flyrise.android3.test.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 类功能描述：</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * 修改时间：</br>
 * 修改备注：</br>
 */
public class PullRefresh extends Activity{

    LinkedList<String> mListItems = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_pull_refresh_1);
        
        ListView lst = (ListView)findViewById(R.id.list);
        
        mListItems = new LinkedList<String>();
        mListItems.addAll(Arrays.asList(mStrings));
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mListItems);

        
        
        //lst.addHeaderView(LayoutInflater.from(this).inflate(R.layout.pull_to_refresh_header,null));
        lst.setAdapter(adapter);
        Log.e("PullRefresh", "SSSS==="+getIntent().getStringExtra("SSSS"));
    }
    
    private String[] mStrings = {
            "1", "2", "3",
            "4", "5", "6", "7", "8",
            "9", "10", "11", "12",
            "13"};
}
