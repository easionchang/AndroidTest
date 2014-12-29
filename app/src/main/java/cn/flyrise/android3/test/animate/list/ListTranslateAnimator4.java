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
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
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
public class ListTranslateAnimator4 extends ListActivity{
    List<Map<String,String>> data;
    SimpleAdapter adpter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mStrings));
        ListView listView = getListView();
       
        AnimationSet set = new AnimationSet(true);

        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(1000);
        set.addAnimation(animation);

        animation = new TranslateAnimation(
            Animation.RELATIVE_TO_PARENT, 0.0f,Animation.RELATIVE_TO_PARENT, 0.0f,
            Animation.RELATIVE_TO_PARENT, -1.0f,Animation.RELATIVE_TO_PARENT, 0.0f
        );
        animation.setDuration(1000);
        set.addAnimation(animation);

        LayoutAnimationController controller = new LayoutAnimationController(set, 0.5f);
        listView.setLayoutAnimation(controller);
        
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
