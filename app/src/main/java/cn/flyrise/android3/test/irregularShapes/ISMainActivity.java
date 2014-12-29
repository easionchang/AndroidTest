/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2014-9-3 下午4:51:09
 */
package cn.flyrise.android3.test.irregularShapes;

import cn.flyrise.android3.test.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
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
public class ISMainActivity extends Activity{
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.is_fragment_layout);
        ImageView img = (ImageView)findViewById(R.id.image);
        Bitmap betty = BitmapFactory.decodeResource(getResources(), R.drawable.betty);  
        Bitmap mask = BitmapFactory.decodeResource(getResources(), R.drawable.mask);
        
        img.setImageBitmap(combineBitmap(mask,betty));
        
        betty.recycle();
        mask.recycle();
    }
    
    
    private Bitmap combineBitmap(Bitmap bg,Bitmap fg){
        Bitmap bmp;
        int width = bg.getWidth()>fg.getWidth()?bg.getWidth():fg.getWidth();
        int height = bg.getHeight()>fg.getHeight()?bg.getHeight():fg.getHeight();
        
        bmp = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
        
        Canvas canvas = new Canvas(bmp);
        canvas.drawBitmap(bg, 0, 0, null);
        canvas.drawBitmap(fg, 0, 0, paint);
        return bmp;
    }
    

}
