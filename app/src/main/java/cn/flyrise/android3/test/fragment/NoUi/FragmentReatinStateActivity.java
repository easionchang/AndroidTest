/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-3-5 ����1:47:43
 */
package cn.flyrise.android3.test.fragment.NoUi;

import cn.flyrise.android3.test.R;

import android.app.Activity;
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
public class FragmentReatinStateActivity extends Activity{
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().add(android.R.id.content,new UIFragment()).commit();
    }
    
    
    public static class UIFragment extends Fragment{
        
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_retain, container, false);
            return view;
        }
        
    }
    

}
