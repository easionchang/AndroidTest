/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2012-11-26 下午06:47:41
 */

package cn.flyrise.android3.test.concurrent.readerwriter;

/**
 * 类功能描述：</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * 修改时间：</br>
 * 修改备注：</br>
 */
public class ReadWriteLock {
    private int readingReaders = 0; // (A)...实际正在读取的执行绪数量
    private int waitingWriters = 0; // (B)...正在等待写入的执行绪数量
    private int writingWriters = 0; // (C)...实际正在写入的执行绪数量
    private boolean preferWriter = true; // 写入优先的话，值为true  仿制读者一直在读，会让写者线程饥饿

    public synchronized void readLock() throws InterruptedException {
        while (writingWriters > 0 || (preferWriter && waitingWriters > 0)) {
            wait();
        }
        readingReaders++; //  (A)实际正在读取的线程数量加1
    }

    public synchronized void readUnlock() {
        readingReaders--; //  (A)实际正在读取的线程数量减1
        preferWriter = true;
        notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
    waitingWriters++;                       // (B)正在等待写入的线程数量加1
    try {
        while (readingReaders > 0 || writingWriters > 0) {
            wait();
        }
    } finally {
      waitingWriters--;                   // (B)正在等待写入的线程数量减1
    }
    writingWriters++;                       //  (C)实际正在写入的线程数量加1
}

    public synchronized void writeUnlock() {
        writingWriters--; // (C)实际正在写入的线程数量减
        preferWriter = false;
        notifyAll();
    }
}
