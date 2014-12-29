/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2012-11-26 上午10:25:05
 */
package cn.flyrise.android3.test.concurrent.productconsumer2;

import cn.flyrise.android3.test.concurrent.productconsumer.TestPCActivity;

import android.util.Log;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 类功能描述：</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * 修改时间：</br>
 * 修改备注：</br>
 */
public class Productor2 implements Runnable{
    
    private String name;

    
    private Product p;
    
    public Productor2(Product p,String name){
        this.p = p;
        this.name = name;
    }

    @Override
    public void run() {
        for(;;){
            try {
                String data = p.create();
                Log.e(TestPCActivity.TAG, "P("+data+")");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
    }
    
    

}
