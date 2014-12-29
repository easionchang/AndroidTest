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
public class OutlineFragment extends Fragment{
    
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collaboration_outline, container, false);
        return view;
    }
    
}
