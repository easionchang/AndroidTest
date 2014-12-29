/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2012-9-26 上午10:56:10
 */
package cn.flyrise.android3.test.common;

import cn.flyrise.android3.test.R;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

/**
 * 类功能描述：</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * 修改时间：</br>
 * 修改备注：</br>
 */
public class TestPaddingActivity extends Activity{
    TextView tv;
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tv = (TextView)findViewById(R.id.tv);
        //
        Log.e("TestPaddingActivity", "tv.getMeasuredHeight()==="+tv.getMeasuredHeight());
        handler.sendEmptyMessageDelayed(1,10);
        
        Log.e("TestPaddingActivity", "SSSS==="+getIntent().getStringExtra("SSSS"));
        
    };
    
    private int i = 1;;
    
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            i++;
            //height = tv.getMeasuredHeight();
            tv.setPadding(0,-55+i, 0, 0);
            Log.e("TestPaddingActivity", "tv.getMeasuredHeight()==="+tv.getMeasuredHeight());
            handler.sendEmptyMessageDelayed(1,100);
        }
        
    };

}
