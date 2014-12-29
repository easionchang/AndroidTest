/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-3-5 ����9:25:48
 */
package cn.flyrise.android3.test.fragment;

import cn.flyrise.android3.test.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * �๦��������</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * �޸�ʱ�䣺</br>
 * �޸ı�ע��</br>
 */
public class ContentModifyFragment extends Fragment{
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collaboration_content_modify, container, false);
        
        DetailSupplementAdapter adpter = new DetailSupplementAdapter(getActivity());
        
        for (int j = 0; j < 10; j++) {
            DetailSupplement supplement = new DetailSupplement("����", "2013-03-05 09:30", "�����޸������޸������޸������޸������޸������޸������޸������޸������޸������޸������޸������޸������޸������޸������޸������޸�", false);
            adpter.addListItemsMembers(supplement);
        }
       
        
        ListViewWithoutScroll list = (ListViewWithoutScroll)view.findViewById(R.id.modification_list);
        list.setAdapter(adpter);
        
        return view;
    }

}
