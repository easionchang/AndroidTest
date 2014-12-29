package cn.flyrise.android3.test.concurrent.readerwriter;

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
                Log.e(ReaderWriterTestActivity.TAG, (Thread.currentThread().getName() + " reads " + String.valueOf(readbuf)));
            }
        } catch (InterruptedException e) {
        }
    }
}
