/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2014-11-4 ����4:25:54
 */
package cn.flyrise.android3.test.graphic;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * �๦��������</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * �޸�ʱ�䣺</br>
 * �޸ı�ע��</br>
 */
public class SlyderActivity extends Activity{

    private SlyderView slyderView;
    private MyHandler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        slyderView = new SlyderView(this);
        setContentView(slyderView);
        slyderView.play();
        handler = new MyHandler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5*1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(1);
            }
        }).start();
        
    }
    
    private class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            //ֹͣ���Ǹ�λ��
            slyderView.stop(3);
        }
    }
}
