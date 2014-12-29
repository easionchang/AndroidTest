/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2014-4-2 下午1:51:20
 */
package cn.flyrise.android3.test.resource;

import cn.flyrise.android3.test.R;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
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
public class StringTestActivity extends Activity{
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        LinearLayout llyMain = new LinearLayout(this);
        llyMain.setOrientation(LinearLayout.VERTICAL);
        LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT);
        llyMain.setLayoutParams(lp);
        
        TextView tv = new TextView(this);
        LayoutParams lp2 = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        llyMain.addView(tv, lp2);
        tv.setText(R.string.test_html_string);
        
        
        String s = this.getResources().getString(R.string.test_html_string);
        TextView tv2 = new TextView(this);
        //LayoutParams lp3 = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        llyMain.addView(tv2, lp2);
        tv2.setText(s);
        Log.e("Test", "==========="+s);
        
        
        TextView tv3 = new TextView(this);
        llyMain.addView(tv3, lp2);
        tv3.setText(Html.fromHtml(s));
        
        setContentView(llyMain);
    }
    
    private void createMainView(){
        
    }

}
