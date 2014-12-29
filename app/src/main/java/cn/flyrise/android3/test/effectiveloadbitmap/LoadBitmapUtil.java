/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2012-12-4 下午05:30:27
 */
package cn.flyrise.android3.test.effectiveloadbitmap;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * 类功能描述：</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * 修改时间：</br>
 * 修改备注：</br>
 */
public class LoadBitmapUtil {
    
    /**
     * 高效的加载图片，如果你的组件只是为了显示100*100的图像
     * 那么你就没有必要加载一个1024*1024大小的图片到内存
     * @param res
     * @param resId
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static Bitmap decodeSampledBitmapFromResource(Resources res,
            int resId, int reqWidth, int reqHeight) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth,
                reqHeight);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options,
            int reqWidth, int reqHeight) {

        // Raw height and width of image
        final int height = options.outHeight;

        final int width = options.outWidth;

        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            if (width > height) {

                inSampleSize = Math.round((float)height / (float)reqHeight);

            } else {

                inSampleSize = Math.round((float)width / (float)reqWidth);

            }
        }
        return inSampleSize;
    }


}
