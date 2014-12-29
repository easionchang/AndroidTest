/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-10-31 ����4:13:58
 */
package cn.flyrise.android3.test.animate.list;


import cn.flyrise.android3.test.R;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
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
public class ListTranslateAnimator2 extends ListActivity{
    List<Map<String,String>> data;
    SimpleAdapter adpter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mStrings));
        ListView listView = getListView();
       
        
        
         Animation animation=AnimationUtils.loadAnimation(this, R.anim.slide_right);
        //�õ�һ��LayoutAnimationController����
        LayoutAnimationController lac=new LayoutAnimationController(animation);

        //���ÿؼ���ʾ��˳��
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
        //���ÿؼ���ʾ���ʱ�䣻
        lac.setDelay(0.3f);
        //ΪListView����LayoutAnimationController���ԣ�
        listView.setLayoutAnimation(lac);
        
    }
    
    private String[] mStrings = {
            "Bordeaux",
            "Lyon",
            "Marseille",
            "Nancy",
            "Paris",
            "Toulouse",
            "Strasbourg"
        };
    

}
