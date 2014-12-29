//
//  ListViewWithoutScroll.java
//  FeOA
//
//  Created by lin yiqi on 2012-2-9.
//  Copyright 2012 flyrise. All rights reserved.
//
package cn.flyrise.android3.test.fragment;

import java.io.InputStream;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.LinearLayout;

/**
 * @author <a href="mailto:184618345@qq.com">017</a>
 */
public class ListViewWithoutScroll extends LinearLayout {
	private Adapter adapter = null;
	private OnItemClickListener onItemClickListener = null;
    private OnItemLongClickListener onItemLongClickListener;
    private Drawable dividerLine;
    private int dividerLineHeight = 3;
    private boolean isFooterDividersEnabled = true;
    private boolean isHeaderDividersEnabled;
    private DataSetObserver dataSetObserver;

	public ListViewWithoutScroll(Context context) {
		super(context);
		dividerLine = dividerLine();
	}

	/**
	 * 允许通过XML的方式注册控�?
	 * 
	 * @param context context
	 * @param attributeSet attribute set
	 */
	public ListViewWithoutScroll(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		dividerLine = dividerLine();
		this.setOrientation(VERTICAL);
	}

	/**
	 * 设置数据.
	 * 
	 * @param adapter data adapter
	 */
	public void setAdapter(Adapter adapter) {
	    if (null != this.adapter) {
	        this.adapter.unregisterDataSetObserver(dataSetObserver);
        }
		this.adapter = adapter;
		if (this.adapter != null) {
		    dataSetObserver = new AdapterDataSetObserver();
		    this.adapter.registerDataSetObserver(dataSetObserver);
		}
		bindLinearLayout();
	}

	/**
	 * 绑定布局
	 */
	private void bindLinearLayout() {
		int count = adapter.getCount();
		this.removeAllViews();
		for (int i = 0; i < count; i++) {
			View v = adapter.getView(i, null, this);
			final int j = i;
			// 为每个view绑定�?��数据，可以是任何对象
			v.setTag(i);
			v.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					ListViewWithoutScroll.this.onItemClick(null, v, j, adapter.getItemId(j));
				}
			});
			v.setOnTouchListener(new OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					
					return false;
				}
			});
			v.setOnLongClickListener(new OnLongClickListener() {
                
                @Override
                public boolean onLongClick(View v) {
                    if(onItemLongClickListener != null){
                        onItemLongClickListener.onItemLongClick(null, v, j, adapter.getItemId(j));
                        return true;
                    }
                    return false;
                }
            });
			if (i==0&&isHeaderDividersEnabled) {
                View divider = new View(getContext());
                divider.setBackgroundDrawable(dividerLine);
                addView(divider, new LayoutParams(LayoutParams.FILL_PARENT, dividerLineHeight));
            }
			addView(v);
			if (i!=count-1||isFooterDividersEnabled) {
			    View divider = new View(getContext());
			    divider.setBackgroundDrawable(dividerLine);
			    addView(divider, new LayoutParams(LayoutParams.FILL_PARENT, dividerLineHeight));
            }
		}
	}
	
	/**分割�?
     */
    private Drawable dividerLine() {
        Bitmap bitmap = Bitmap.createBitmap(1, 4, Config.ARGB_4444);
        Canvas cv = new Canvas(bitmap);
        cv.drawColor(Color.WHITE);
        Paint p1 = new Paint();
        p1.setStyle(Paint.Style.STROKE);// 设置画笔类型
//        p1.setStrokeCap(Paint.Cap.ROUND);// 设置端点处为圆点
        p1.setStrokeWidth(2);// 设置画笔粗细

        p1.setColor(0xffc5c4c1);
        cv.drawPoint(1, 1, p1);
        Drawable drawable = new BitmapDrawable(bitmap);
        return drawable;
    }
    /**
     * 设置分割�?
     */
    public void setDivider(int resID) {
        InputStream is = getContext().getResources().openRawResource(resID);
        dividerLine = Drawable.createFromStream(is , null);
    }
    /**
     * 设置分割线的高度
     */
    public void setDividerHeight(int dividerLineHeight) {
        this.dividerLineHeight = dividerLineHeight;
    }
    /**
     * 设置分割�?
     */
    public void setDivider(Drawable divider) {
        dividerLine = divider;
    }
    
    /**
     * 启用或禁用页脚分割线(默认是启�?true)
     */
    public void setFooterDividersEnabled(boolean footerDividersEnabled) {
        this.isFooterDividersEnabled = footerDividersEnabled;
    }
    /**
     * 启用或禁用页头分割线�?
     */
    public void setHeaderDividersEnabled(boolean headerDividersEnabled) {
        this.isHeaderDividersEnabled = headerDividersEnabled;
    }

	
	public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
		this.onItemClickListener = onItemClickListener;
	}
	
	/**
     * 设置长按事件
     */
	public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
	    this.onItemLongClickListener = onItemLongClickListener;
    }
	/**
     * @param arg0 null...
     * @param arg1 这个view,要获得Adapter要墙转换
     * @param arg2 position
     * @param arg3 这个是你设的�?
     */
	private void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		if (onItemClickListener != null) {
			onItemClickListener.onItemClick(arg0, arg1, arg2, arg3);
		}
	}

	public Adapter getAdapter() {
		return adapter;
	}
	
	class AdapterDataSetObserver extends DataSetObserver {
        @Override
        public void onChanged() {
            bindLinearLayout();
        }
        @Override
        public void onInvalidated() {
            bindLinearLayout();
        }
        public void clearSavedState() {
        }
    }
}
