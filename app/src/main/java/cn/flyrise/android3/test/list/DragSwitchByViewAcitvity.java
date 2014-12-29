/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2012-9-15 ����09:43:14
 */
package cn.flyrise.android3.test.list;

import cn.flyrise.android3.test.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * �๦��������</br>
 * ListView��ק����ͨ���Զ����VIew
 * �ô��ǿ���ֱ��ʹ���Զ����ListView
 * @author zms
 * @version 1.0
 * </p>
 * �޸�ʱ�䣺</br>
 * �޸ı�ע��</br>
 */
public class DragSwitchByViewAcitvity extends Activity{
    
    ListView lstv;
    List<Map<String,String>> data;
    SimpleAdapter adapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_main);
        
       
       
        
        lstv = (ListView)findViewById(R.id.list);
        
        
        String[] from = new String[]{"key","value"};
        int[] to = new int[]{R.id.key,R.id.value};
        data = getData();
        adapter = new SimpleAdapter(this,data , R.layout.list_item, from, to);
        
        lstv.setAdapter(adapter);
        
    }
    
    private List<Map<String,String>> getData(){
        List<Map<String,String>> data = new ArrayList<Map<String,String>>();
        
        for(int i=1;i<54;i++){
            Map<String,String> map = new HashMap<String,String>();
            if(i>=10){
                map.put("key", "2012-08-"+i);
            }else{
                map.put("key", "2012-08-0"+i);
            }
            map.put("value", "������־");
            data.add(map);
        }
        
        return data;
    }

}
