/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-5-3 下午2:48:56
 */
package cn.flyrise.android3.test.custom;

import cn.flyrise.android3.test.R;

import android.app.Activity;
import android.os.Bundle;

/**
 * 类功能描述：</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * 修改时间：</br>
 * 修改备注：</br>
 */
public class SearchActivity extends Activity{
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_search_view);
    }

}
