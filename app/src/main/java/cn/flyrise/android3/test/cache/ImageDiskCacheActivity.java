/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2012-12-7 下午02:47:48
 */
package cn.flyrise.android3.test.cache;

import cn.flyrise.android3.test.R;


import android.app.Activity;

import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
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
public class ImageDiskCacheActivity extends Activity{
    private static final int DISK_CACHE_SIZE = 1024 * 1024 * 10; // 10MB

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loadbitmap);
        ImageView iv = (ImageView)findViewById(R.id.iv);
        DiskLruImageCache imageCache = new DiskLruImageCache(this, "yy", DISK_CACHE_SIZE, CompressFormat.PNG, 70);
        //imageCache.put("myPic", BitmapFactory.decodeResource(getResources(), R.drawable.welcome_fe));
        iv.setImageBitmap(imageCache.getBitmap("myPic"));
    }
    
   
}
