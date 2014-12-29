/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2014-11-5 上午9:59:18
 */
package cn.flyrise.android3.test.view;

import cn.flyrise.android3.test.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

/**
 * 类功能描述：</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * 修改时间：</br>
 * 修改备注：</br>
 */
public class DrawerActivity extends FragmentActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.draw_activity);
    }
    
    
    

}
