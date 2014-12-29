/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-3-13 下午2:03:56
 */
package cn.flyrise.android3.test.layout.relative;

import cn.flyrise.android3.test.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 类功能描述：</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * 修改时间：</br>
 * 修改备注：</br>
 */
public class RelativeLayoutTest1Activity extends Activity{
    public static int count = 4;
    static DisplayMetrics dm = new DisplayMetrics();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        setContentView(R.layout.layout_relative_test1);
       
    }
    
    
    
    public static class MyView extends RelativeLayout{
        int startMove = 0;
        
        ImageView imageView;
        
        private ViewPager viewPager;
        
        List<ImageView> viewPagerViews = new ArrayList<ImageView>();
        
        public MyView(Context context) {
            this(context, null);
        }
        
        public MyView(Context context, AttributeSet attrs) {
            super(context, attrs);
            viewPager = new ViewPager(context);
            bindData(context);
            init(context);
        }
        
        private void bindData(Context context){
            int[] drawables = new int[]{R.drawable.guide01_fe,R.drawable.guide02_fe,R.drawable.guide03_fe,R.drawable.guide04_fe};
            for(int i = 0;i<drawables.length;i++){
                ImageView img = new ImageView(context);
                img.setLayoutParams(new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
                img.setImageResource(drawables[i]);
                viewPagerViews.add(img);
            }
            
            
            viewPager.setAdapter(new PagerAdapter() {
                
                @Override
                public boolean isViewFromObject(View arg0, Object arg1) {
                    return arg0 == arg1;
                }
                
                public Object instantiateItem(View container, int position) {
                    ((ViewPager)container).addView(viewPagerViews.get(position));
                    return viewPagerViews.get(position);
                }

                @Override
                public int getCount() {
                    // TODO Auto-generated method stub
                    return viewPagerViews.size();
                }
                
                public void destroyItem(View container, int position, Object object) {
                    ((ViewPager)container).removeView(viewPagerViews.get(position));
                }
            });
            
            viewPager.setOnPageChangeListener(new OnPageChangeListener() {
                
                @Override
                public void onPageSelected(int arg0) {
                    Log.e("Test", "arg0==========="+arg0);
                    moveTO(arg0+100000);
                    
                }
                
                @Override
                public void onPageScrolled(int arg0, float arg1, int arg2) {
                    // TODO Auto-generated method stub
                    
                }
                
                @Override
                public void onPageScrollStateChanged(int arg0) {
                    // TODO Auto-generated method stub
                    
                }
            });
        }
        
        private void init(Context context){
            LinearLayout titleLayout = new LinearLayout(context);
            titleLayout.setId(10);
            LinearLayout.LayoutParams titleLp = new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT);
            titleLp.weight = 1;
            //titleLp.gravity = Gravity.CENTER;
            
            for (int i=0;i<count;i++) {
                RelativeLayout titleItem = new RelativeLayout(context);
                titleItem.setId(100000+i);
                titleItem.setGravity(Gravity.CENTER);
                
                TextView titleNameTv = new TextView(context);
                titleNameTv.setText("项目"+i);
                titleNameTv.setId(100+i);
                titleNameTv.setTextSize(17);
                RelativeLayout.LayoutParams lpm = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                titleItem.addView(titleNameTv, lpm);
                
                TextView titleTipTv = new TextView(context);
                titleTipTv.setText("1");
                titleTipTv.setId(1000+i);
                titleTipTv.setTextSize(9);
                RelativeLayout.LayoutParams lpm2 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                lpm2.addRule(RelativeLayout.RIGHT_OF, 100+i);
                titleTipTv.setLayoutParams(lpm2);
                titleItem.addView(titleTipTv);
                
                titleItem.setLayoutParams(titleLp);
                titleLayout.addView(titleItem);
                
                titleItem.setOnClickListener(new MyOnClickListener(100000+i));
            }
            
           RelativeLayout.LayoutParams lpm = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
           addView(titleLayout, lpm);
           
         
           imageView = initImageView(context,R.drawable.list_title_on_fe);
           imageView.setScaleType(ScaleType.MATRIX);
           imageView.setImageResource(R.drawable.list_title_on_fe);
           
           RelativeLayout.LayoutParams imageLpm = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
           imageLpm.addRule(RelativeLayout.BELOW, 10);
           imageView.setLayoutParams(imageLpm);
           imageView.setId(99);
           addView(imageView);
           
           RelativeLayout.LayoutParams viewPageLp = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
           viewPageLp.addRule(RelativeLayout.BELOW, 99);
           viewPager.setLayoutParams(viewPageLp);
           addView(viewPager);
        }
        
        public ImageView initImageView(Context context, int imageRes) {
            ImageView iv = new ImageView(context);
            int bmpW = BitmapFactory.decodeResource(getResources(), imageRes).getWidth();// 获取图片宽度
          
         
            int screenW = dm.widthPixels;// 获取分辨率宽度
            Log.e("Test", "screenW=======>"+screenW);
            int offset = 0;
            if (count != 0) {
                offset = (screenW / count - bmpW) / 2;// 计算偏移量
            }
            Matrix matrix = new Matrix();
            
            matrix.postTranslate(0, 0);
            matrix.postScale(screenW / (count * bmpW * 1.0f), 1);
            iv.setImageMatrix(matrix);// 设置图片初始位置
            return iv;
        }
        
        
        class MyOnClickListener implements OnClickListener{
            int i;
            
            public MyOnClickListener(int i){
                this.i = i;
            }
          
            public void onClick(View v) {
                viewPager.setCurrentItem(i - 100000, true);
                MyView.this.moveTO(i);
            }
            
        }
        
        private void moveTO(int index){
            int moveTo = (index - 100000) * dm.widthPixels/count;
            TranslateAnimation anim = new TranslateAnimation(startMove, moveTo, 0, 0);
            startMove = moveTo;
            anim.setDuration(200);
            anim.setFillAfter(true);
            imageView.startAnimation(anim);
        }
        
    }
    
   

}
