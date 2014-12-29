/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-10-31 下午4:13:58
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
 * 类功能描述：</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * 修改时间：</br>
 * 修改备注：</br>
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
        //得到一个LayoutAnimationController对象；
        LayoutAnimationController lac=new LayoutAnimationController(animation);

        //设置控件显示的顺序；
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
        //设置控件显示间隔时间；
        lac.setDelay(0.3f);
        //为ListView设置LayoutAnimationController属性；
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
