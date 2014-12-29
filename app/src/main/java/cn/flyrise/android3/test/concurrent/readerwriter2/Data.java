/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2012-11-26 下午07:26:34
 */
package cn.flyrise.android3.test.concurrent.readerwriter2;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 类功能描述：</br>
 *
 * @author zms
 * @version 1.0
 * 
 * </p>
 * 修改时间：</br>
 * 修改备注：</br>
 */
public class Data {
    private final char[] buffer;
    
    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock read = readWriteLock.readLock();
    private final Lock write = readWriteLock.writeLock();

    public Data(int size) {
        this.buffer = new char[size];
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = '*';
        }
    }
    public char[] read() throws InterruptedException {
        read.lock();
        try {
            return doRead();
        } finally {
            read.unlock();
        }
    }
    public void write(char c) throws InterruptedException {
        write.lock();
        try {
            doWrite(c);
        } finally {
            write.unlock();
        }
    }
    private char[] doRead() {
        char[] newbuf = new char[buffer.length];
        for (int i = 0; i < buffer.length; i++) {
            newbuf[i] = buffer[i];
        }
        slowly();
        return newbuf;
    }
    private void doWrite(char c) {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = c;
            slowly();
        }
    }
    private void slowly() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
        }
    }

}
