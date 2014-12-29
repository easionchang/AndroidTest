/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2012-11-26 下午01:31:32
 */
package cn.flyrise.android3.test.concurrent.productconsumer2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 类功能描述：</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * 修改时间：</br>
 * 修改备注：</br>
 */
public class Product {
    
    private int maxSize = 10;
    
    private Queue<String> data = new LinkedList<String>();
    
    private int i;
    
    public synchronized String create(){
        while(data.size() == maxSize){
             try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String d = i+"";
        i++;
        
        data.add(d);
        notifyAll();
        return d;
    }
    
    public synchronized String consume(){
        while(data.size() == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String d = data.poll();
        notifyAll();
        return d;
    }

}
