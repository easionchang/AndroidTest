/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-3-2 ����1:25:16
 */

package cn.flyrise.android3.test.actionbar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

/**
 * �๦��������</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * �޸�ʱ�䣺</br>
 * �޸ı�ע��</br>
 */
public class ActionbarTestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //androidVersion 11����Ĭ��titleBar����ActionBar,���д���Ϳ���ȥ��
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        menu.add("���");
        
//        MenuItem actionItem = menu.add("Action");
//        actionItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
//        actionItem.setIcon(android.R.drawable.ic_dialog_map);
//        
//        
        MenuItem actionItem1 = menu.add("Action1");
        actionItem1.setShowAsAction(MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        actionItem1.setIcon(android.R.drawable.ic_dialog_dialer);
//
//        
//        
//        MenuItem actionItem2 = menu.add("Action2");
//        actionItem2.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
//        actionItem2.setIcon(android.R.drawable.ic_delete);
//
//        
        
//        MenuItem actionItem3 = menu.add("Action3");
//        actionItem3.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
//        actionItem3.setIcon(android.R.drawable.ic_secure);

        return super.onCreateOptionsMenu(menu);
    }

}
