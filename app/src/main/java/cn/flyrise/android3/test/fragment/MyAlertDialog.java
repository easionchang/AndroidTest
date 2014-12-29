/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-3-4 下午2:44:00
 */
package cn.flyrise.android3.test.fragment;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;

/**
 * 类功能描述：</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * 修改时间：</br>
 * 修改备注：</br>
 */
public class MyAlertDialog {
    
    private Context mContext;
    
    public MyAlertDialog(Context context){
        mContext = context;
    }

    
    public void show(){
        Builder builder = new Builder(mContext);
        builder.setTitle("普通Dialog").setIcon(android.R.drawable.ic_dialog_alert).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                
            }
        }).create().show();
    }
}
