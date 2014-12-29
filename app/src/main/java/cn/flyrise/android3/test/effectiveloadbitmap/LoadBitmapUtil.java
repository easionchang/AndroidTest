/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2012-12-4 ����05:30:27
 */
package cn.flyrise.android3.test.effectiveloadbitmap;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * �๦��������</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * �޸�ʱ�䣺</br>
 * �޸ı�ע��</br>
 */
public class LoadBitmapUtil {
    
    /**
     * ��Ч�ļ���ͼƬ�����������ֻ��Ϊ����ʾ100*100��ͼ��
     * ��ô���û�б�Ҫ����һ��1024*1024��С��ͼƬ���ڴ�
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
