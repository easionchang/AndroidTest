/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-3-4 下午4:53:16
 */
package cn.flyrise.android3.test.fragment;

import cn.flyrise.android3.test.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 类功能描述：</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * 修改时间：</br>
 * 修改备注：</br>
 */
public class ContentFragment extends Fragment{
    
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collaboration_detail_content, container, false);
        
        DetailSupplementAdapter adpter = new DetailSupplementAdapter(getActivity());
        
        for (int j = 0; j < 10; j++) {
            DetailSupplement supplement = new DetailSupplement("李四", "2013-03-05 09:23", "正文补充正文补充正文补充正文补充正文补充正文补充正文补充正文补充正文补充正文补充正文补充正文补充", false);
            adpter.addListItemsMembers(supplement);
        }
       
        
        
       
        
        ListViewWithoutScroll list = (ListViewWithoutScroll)view.findViewById(R.id.supplement_list);
        list.setAdapter(adpter);
        return view;
    }
    
}
