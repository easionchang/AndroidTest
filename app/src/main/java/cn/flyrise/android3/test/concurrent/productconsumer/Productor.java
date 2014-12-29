/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2012-11-26 ����10:25:05
 */
package cn.flyrise.android3.test.concurrent.productconsumer;

import android.util.Log;

import java.util.concurrent.ArrayBlockingQueue;
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
public class Productor implements Runnable{
    
    private String name;
    
    private int i;
    
    private BlockingQueue<String> queue;
    
    public Productor(BlockingQueue<String> queue,String name){
        this.queue = queue;
        this.name = name;
    }

    @Override
    public void run() {
        for(;;){
            try {
                String p = name+"-"+i;
                queue.put(p);
                i++;
                Log.e(TestPCActivity.TAG, "P("+p+")");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
    }
    
    

}
