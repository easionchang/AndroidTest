/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2014-3-13 下午4:23:56
 */
package cn.flyrise.android3.test.effectiveloadbitmap;

import cn.flyrise.android3.test.R;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;

/**
 * 类功能描述：</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * 修改时间：</br>
 * 修改备注：</br>
 */
public class BitmapMemoryTestActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView imgView = new ImageView(this);
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        //imgView.setBackgroundDrawable();
        Bitmap bp = LoadBitmapUtil.decodeSampledBitmapFromResource(getResources(), R.drawable.big_map,480, 800);
        imgView.setImageBitmap(bp);
        setContentView(imgView);
        Log.d("BitmapMemoryTestActivity", "getByteCount=="+bp.getByteCount());


        ActivityManager activityManager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        MemoryInfo info = new MemoryInfo();
        activityManager.getMemoryInfo(info);
        
        Log.d("BitmapMemoryTestActivity", "getByteCount=="+bp.getByteCount());
        Log.d("BitmapMemoryTestActivity", "getMemoryClass=="+activityManager.getMemoryClass());
        Log.d("BitmapMemoryTestActivity", "availMem=="+info.availMem);
    }

}
