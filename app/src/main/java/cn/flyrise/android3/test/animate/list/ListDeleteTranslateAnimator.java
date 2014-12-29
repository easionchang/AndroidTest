/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-10-31 下午4:13:58
 */
package cn.flyrise.android3.test.animate.list;

import cn.flyrise.android3.test.R;
import cn.flyrise.android3.test.animate.list.adapte.Data;
import cn.flyrise.android3.test.animate.list.adapte.ListDeleteAnimateAdapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
public class ListDeleteTranslateAnimator extends Activity{
    List<Map<String,String>> data;
    ListDeleteAnimateAdapter adpter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_1);
        ListView lstView = (ListView)findViewById(R.id.list_view);

        adpter = new ListDeleteAnimateAdapter(this,getData());
        lstView.setAdapter(adpter);
        
        lstView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                adpter.animateItem(position,id);
                //adpter.notifyDataSetChanged();
            }
        });
        
        
        /* 使用代码的方式设置动画
         Animation animation=AnimationUtils.loadAnimation(this, R.anim.list_anim_layout);
        //得到一个LayoutAnimationController对象；
        LayoutAnimationController lac=new LayoutAnimationController(animation);

        //设置控件显示的顺序；
        lac.setOrder(LayoutAnimationController.ORDER_REVERSE);
        //设置控件显示间隔时间；
        lac.setDelay(1);
        //为ListView设置LayoutAnimationController属性；
        lstView.setLayoutAnimation(lac);
        */
        
    }
    
    public List<Data> getData(){
        List<Data> data = new ArrayList<Data>();
        for(int i=1;i<=30;i++){
            Data dt = new Data();
            dt.setId(i);
            dt.setInfo("数据"+i);
            data.add(dt);
        }
        return data;
    }
    

}
