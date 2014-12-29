package cn.flyrise.android3.test.loader;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;

/**
 * Created by zms on 2014-12-17.
 */
public class StringLoder extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CursorLoaderActivity activity = new CursorLoaderActivity() ;
       // CursorLoaderActivity.MyListFragment.getString();
      //  CursorLoaderActivity.MyListFragment f = new CursorLoaderActivity.MyListFragment();
       // outer.inner  obj = outerobj.new inner()
       // CursorLoaderActivity.MyNoStaticFragment noStaticFragment = activity.new MyNoStaticFragment();
    }
}
