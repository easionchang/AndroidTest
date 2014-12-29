/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-3-4 下午2:32:03
 */
package cn.flyrise.android3.test.fragment;

import cn.flyrise.android3.test.R;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 类功能描述：</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * 修改时间：</br>
 * 修改备注：</br>
 */
public class AlertDialogActivity extends Activity{
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_alertdialog);
        
        Button btn1 = (Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                MyAlertDialog dialog = new MyAlertDialog(AlertDialogActivity.this);
                dialog.show();
            }
        });
        
        
        Button btn2 = (Button)findViewById(R.id.btn2);
        btn2.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                new MyAlertDialogFragment().show(getFragmentManager(), "dialog");
            }
        });
    }

}
