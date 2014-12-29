package cn.flyrise.android3.test.loader;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.flyrise.android3.test.R;

/**
 * Created by zms on 2014-12-17.
 */
public class CursorLoaderActivity extends Activity{



    private final Handler mLeakyHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // ...
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Post a message and delay its execution for 10 minutes.
        mLeakyHandler.postDelayed(new Runnable() {
            @Override
            public void run() { /* ... */ }
        }, 1000 * 60 * 10);

        // Go back to the previous Activity.
        //finish();
        Toast.makeText(this,"Hello",Toast.LENGTH_SHORT).show();
    }


}
