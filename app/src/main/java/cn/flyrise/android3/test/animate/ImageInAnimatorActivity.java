package cn.flyrise.android3.test.animate;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;

import cn.flyrise.android3.test.R;
import cn.flyrise.android3.test.util.DisplayUtil;

/**
 * Created by zms on 2014-12-23.
 */
public class ImageInAnimatorActivity extends Activity{
    private  int location[];
    private ImageView imgView;
    private View contentView;
    private ColorDrawable backgroundColor;
    private View appView;
    private int orgWidth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.frame_main);
        location = this.getIntent().getIntArrayExtra("XYWH");
        Log.e("Test", "x2=" + location[0] + " y2=" + location[1]+" w2="+location[2]+" h2="+location[3]);
        imgView = (ImageView)findViewById(R.id.img_view);
        contentView = findViewById(R.id.frame_main);
        appView = findViewById(android.R.id.content);
        appView.setBackgroundColor(Color.BLACK);

        imgView.setLayoutParams(new FrameLayout.LayoutParams(location[2],location[3]));
        imgView.setX(location[0]);
        //Y的值是相对屏幕的坐标，而不是父View；所以要减去状态栏的高度（标题栏在该页面不显示）
        imgView.setY(location[1] - DisplayUtil.getStatusBarHeight());

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),R.drawable.beach);
        orgWidth = Math.max(bitmap.getHeight(), bitmap.getWidth());

        ObjectAnimator alphaAnimator = ObjectAnimator.ofInt(appView.getBackground(),"alpha",0,255);
        alphaAnimator.start();

        imgView.setPivotX(0);
        imgView.setPivotY(0);
        imgView.animate().scaleX(orgWidth / (float) location[2])
                .scaleY(orgWidth / (float) location[2])
                .translationX(DisplayUtil.getScreenWidth() / 2 - orgWidth / 2)
                .translationY((DisplayUtil.getScreenHeight() - DisplayUtil.getStatusBarHeight()) / 2 - orgWidth / 2);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        ObjectAnimator alphaAnimator = ObjectAnimator.ofInt(appView.getBackground(),"alpha",255,0);
        alphaAnimator.start();
        //TODO 为什么是scaleX（1）？？虽然被放大了但是实际的尺寸还是开始的值
        // Y的值是相对屏幕的坐标，而不是父View；所以要减去状态栏的高度（标题栏在该页面不显示）
        imgView.animate().scaleX(1)
                .scaleY(1)
                .translationX(location[0])
                .translationY(location[1] - DisplayUtil.getStatusBarHeight());
        alphaAnimator.addListener(new AnimatorListenerAdapter() {

            public void onAnimationEnd(Animator animation) {
                ImageInAnimatorActivity.this.finish();
            }

        });
     }
}
