/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-3-4 下午2:51:47
 */
package cn.flyrise.android3.test.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
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
public class MyAlertDialogFragment extends DialogFragment{
    
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Builder builder = new Builder(getActivity());
        builder.setTitle("Dialog Fragment").setIcon(android.R.drawable.ic_dialog_alert).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                
            }
        });
        return builder.create();
    }

}
