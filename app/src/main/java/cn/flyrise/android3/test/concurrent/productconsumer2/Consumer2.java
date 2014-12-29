/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2012-11-26 下午01:25:43
 */
package cn.flyrise.android3.test.concurrent.productconsumer2;

import cn.flyrise.android3.test.concurrent.productconsumer.TestPCActivity;

import android.util.Log;

import java.util.List;

/**
 * 类功能描述：</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * 修改时间：</br>
 * 修改备注：</br>
 */
public class Consumer2 implements Runnable{
    
    private Product p;
    
    private String name;
    
    public Consumer2(Product p,String name){
        this.p = p;
        this.name = name;
    }

    @Override
    public void run() {
        for(;;){
            Log.e(TestPCActivity.TAG, name+"("+p.consume()+")");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    } 
    
    
    
   
    

}
