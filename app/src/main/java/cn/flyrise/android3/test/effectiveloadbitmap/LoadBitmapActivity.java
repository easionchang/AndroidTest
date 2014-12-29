/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2012-12-4 ����03:58:35
 */

package cn.flyrise.android3.test.effectiveloadbitmap;

import cn.flyrise.android3.test.R;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

/**
 * �๦��������</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * �޸�ʱ�䣺</br>
 * �޸ı�ע��</br>
 */
public class LoadBitmapActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loadbitmap);
       ImageView iv = (ImageView)findViewById(R.id.iv);
       BitmapDrawable bd = new BitmapDrawable(LoadBitmapUtil.decodeSampledBitmapFromResource(getResources(), R.drawable.welcome_fe, 100, 100));
       iv.setBackgroundDrawable(bd);
    }
  
}
