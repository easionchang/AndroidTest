/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2012-11-26 ����10:30:48
 */
package cn.flyrise.android3.test.concurrent.productconsumer;

import android.util.Log;

import java.util.concurrent.BlockingQueue;

/**
 * �๦��������</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * �޸�ʱ�䣺</br>
 * �޸ı�ע��</br>
 */
public class Consumer implements Runnable{
    private BlockingQueue<String> queue;
    private String name;
    
    public Consumer(BlockingQueue<String> queue,String name){
        this.queue = queue;
        this.name = name;
    }

   
    public void run() {
        for(;;){
            try {
                Thread.sleep(10000);
                Log.e(TestPCActivity.TAG, name+"("+queue.take()+")");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }

}
