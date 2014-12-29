package cn.flyrise.android3.test.concurrent.readerwriter2;

import android.util.Log;

public class ReaderThread extends Thread {
    private final Data data;
    public ReaderThread(Data data) {
        this.data = data;
    }
    public void run() {
        try {
            while (true) {
                char[] readbuf = data.read();
                Log.e(ReaderWriterTest2Activity.TAG, (Thread.currentThread().getName() + " reads " + String.valueOf(readbuf)));
            }
        } catch (InterruptedException e) {
        }
    }
}
