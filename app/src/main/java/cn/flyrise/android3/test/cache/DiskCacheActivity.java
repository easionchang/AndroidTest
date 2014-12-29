/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2012-12-7 下午02:47:48
 */
package cn.flyrise.android3.test.cache;

import cn.flyrise.android3.test.cache.DiskLruCache.Editor;
import cn.flyrise.android3.test.cache.DiskLruCache.Snapshot;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;

/**
 * 类功能描述：</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * 修改时间：</br>
 * 修改备注：</br>
 */
public class DiskCacheActivity extends Activity{

    private DiskLruCache mDiskCache;
    private static final int DISK_CACHE_SIZE = 1024 * 1024 * 10; // 10MB
    private static final String DISK_CACHE_SUBDIR = "yy";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheDir = getCacheDir(this, DISK_CACHE_SUBDIR);
        Log.e("DiskCacheActivity", "cacheDir=="+cacheDir.getAbsolutePath());
//        try {
//            mDiskCache = DiskLruCache.open(cacheDir, 10,1, DISK_CACHE_SIZE);
//            Editor e = mDiskCache.edit("zms");
//            e.set(0, "datazms------");
//            e.commit();
//            mDiskCache.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
       
        
        
        try {
            mDiskCache = DiskLruCache.open(cacheDir, 10,1, DISK_CACHE_SIZE);
            Snapshot ss = mDiskCache.get("zms");
            Log.e("DiskCacheActivity", "ss =="+ss);
            Log.e("DiskCacheActivity", "ss =="+ss+"    cache value is :=="+ss.getString(0));;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    public static File getCacheDir(Context context, String uniqueName) {
        // Check if media is mounted or storage is built-in, if so, try and use external cache dir
        // otherwise use internal cache dir
        String cachePath=
                        Environment.getExternalStorageDirectory().getPath() ;
        return new File(cachePath + File.separator + uniqueName);
     }
}
