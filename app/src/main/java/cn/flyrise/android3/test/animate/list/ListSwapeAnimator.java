/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-10-31 ����4:13:58
 */
package cn.flyrise.android3.test.animate.list;

import cn.flyrise.android3.test.R;
import cn.flyrise.android3.test.animate.list.adapte.Data;
import cn.flyrise.android3.test.animate.list.adapte.ListDeleteAnimateAdapter;
import cn.flyrise.android3.test.animate.list.adapte.ListSwapeAnimateAdapter;

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
 * �๦��������</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * �޸�ʱ�䣺</br>
 * �޸ı�ע��</br>
 */
public class ListSwapeAnimator extends Activity{
    List<Map<String,String>> data;
    ListSwapeAnimateAdapter adpter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_1);
        ListView lstView = (ListView)findViewById(R.id.list_view);

        adpter = new ListSwapeAnimateAdapter(this,getData(),lstView);
        lstView.setAdapter(adpter);
        
        
        
        /* ʹ�ô���ķ�ʽ���ö���
         Animation animation=AnimationUtils.loadAnimation(this, R.anim.list_anim_layout);
        //�õ�һ��LayoutAnimationController����
        LayoutAnimationController lac=new LayoutAnimationController(animation);

        //���ÿؼ���ʾ��˳��
        lac.setOrder(LayoutAnimationController.ORDER_REVERSE);
        //���ÿؼ���ʾ���ʱ�䣻
        lac.setDelay(1);
        //ΪListView����LayoutAnimationController���ԣ�
        lstView.setLayoutAnimation(lac);
        */
        
    }
    
    public List<Data> getData(){
        List<Data> data = new ArrayList<Data>();
        for(int i=1;i<=30;i++){
            Data dt = new Data();
            dt.setId(i);
            dt.setInfo("����"+i);
            data.add(dt);
        }
        return data;
    }
    

}
