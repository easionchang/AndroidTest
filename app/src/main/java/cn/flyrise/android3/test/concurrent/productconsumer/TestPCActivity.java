/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2012-11-26 上午10:37:29
 */
package cn.flyrise.android3.test.concurrent.productconsumer;

import cn.flyrise.android3.test.concurrent.productconsumer2.Consumer2;
import cn.flyrise.android3.test.concurrent.productconsumer2.Product;
import cn.flyrise.android3.test.concurrent.productconsumer2.Productor2;

import android.app.Activity;
import android.os.Bundle;

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
public class TestPCActivity extends Activity{
    
    public static String TAG = "TestPCActivity";
    
    BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Product p = new Product();
        Thread p1 = new Thread(new Productor2(p, "P1"));
        p1.start();
        for(int i=0;i<1;i++){
            Thread c1 = new Thread(new Consumer2(p,"C"+i));
            c1.start();
        }
        
     
        
        
    }

}
