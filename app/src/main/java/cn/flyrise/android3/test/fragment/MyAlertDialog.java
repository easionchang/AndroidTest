/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-3-4 ����2:44:00
 */
package cn.flyrise.android3.test.fragment;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;

/**
 * �๦��������</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * �޸�ʱ�䣺</br>
 * �޸ı�ע��</br>
 */
public class MyAlertDialog {
    
    private Context mContext;
    
    public MyAlertDialog(Context context){
        mContext = context;
    }

    
    public void show(){
        Builder builder = new Builder(mContext);
        builder.setTitle("��ͨDialog").setIcon(android.R.drawable.ic_dialog_alert).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                
            }
        }).create().show();
    }
}
