package cn.flyrise.android3.test.concurrent.readerwriter2;
import android.app.Activity;
import android.os.Bundle;


public class ReaderWriterTest2Activity extends Activity{
    public static String TAG = "ReaderWriterTest2Activity";
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Data data = new Data(10);
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new WriterThread(data, "ABCDEFGHIJKLMNOPQRSTUVWXYZ").start();
        new WriterThread(data, "abcdefghijklmnopqrstuvwxyz").start();
    }
    
    
    
   
}
