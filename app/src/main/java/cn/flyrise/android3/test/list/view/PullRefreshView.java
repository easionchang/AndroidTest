/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2012-9-24 下午04:50:52
 */

package cn.flyrise.android3.test.list.view;

import cn.flyrise.android3.test.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;

/**
 * 类功能描述：</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * 修改时间：</br>
 * 修改备注：</br>
 */
public class PullRefreshView extends ListView implements OnScrollListener {

    private static final String TAG = "PullRefreshView";

    private static final int TAP_TO_REFRESH = 1;
    private static final int PULL_TO_REFRESH = 2;
    private static final int RELEASE_TO_REFRESH = 3;
    private static final int REFRESHING = 4;

    private int mLastMotionY;

    private RelativeLayout mRefreshView;

    private TextView mRefreshViewText;

    private int mRefreshState;

    private int mRefreshViewHeight;

    private int Height;

    private boolean isRecored;

    public PullRefreshView(Context context) {
        super(context);
        init(context);
    }

    public PullRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PullRefreshView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        Log.e(TAG, "init>>>>>>>>>>>>>>>>>");

        LayoutInflater mInflater = (LayoutInflater)context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mRefreshView = (RelativeLayout)mInflater.inflate(
                R.layout.pull_to_refresh_header, this, false);

        mRefreshViewText = (TextView)mRefreshView
                .findViewById(R.id.pull_to_refresh_text);

        addHeaderView(mRefreshView);
        //mRefreshView.setVisibility(View.GONE);
        measureView(mRefreshView);
        mRefreshViewHeight = mRefreshView.getMeasuredHeight();
        Height = mRefreshView.getMeasuredHeight();
        super.setOnScrollListener(this);
    }

    int lastPositionY = 0;

    public boolean onTouchEvent(MotionEvent event) {
        final int y = (int) event.getY();
        switch(event.getAction()){
            case MotionEvent.ACTION_MOVE:
                if (!isRecored
                        && (getFirstVisiblePosition() == 0 )){
                    lastPositionY = y;
                    isRecored = true;
                    Log.e(TAG, "isRecored===="+isRecored);
                }
                
                if(isRecored){
                    mRefreshView.setPadding(0,
                            (y - lastPositionY) / 2 - Height, 0, 0);
                    Log.e(TAG, "(y - lastPositionY) / 2===="+(y - lastPositionY) / 2);
                }
                
                                
                break;
            case MotionEvent.ACTION_DOWN:
              
                break;
            case MotionEvent.ACTION_UP:
                isRecored = false;
                mRefreshView.setPadding(0,
                        - Height, 0, 0);
                break;
        }
        
        return super.onTouchEvent(event);
    }

    public void onScroll(AbsListView view, int firstVisibleItem,
            int visibleItemCount, int totalItemCount) {
        // if (mCurrentScrollState == SCROLL_STATE_TOUCH_SCROLL){
        // Log.e(TAG, "()=="+mRemRefreshView.getTopfreshView.getTop());
        //        if (firstVisibleItem == 0) {
        //            Log.e(TAG, "mRefreshView.getTop()=="+mRefreshView.getTop());
        
        
        //            if(mRefreshView.getTop() >= 0){
        //                mRefreshViewText.setText("松...");
        //                mRefreshState = RELEASE_TO_REFRESH;
        //            }else{
        //                mRefreshViewText.setText("拉...");
        //                mRefreshState = PULL_TO_REFRESH;
        //            }
        //        }
        //   }
    }

    private void applyHeaderPadding(MotionEvent ev) {
        // getHistorySize has been available since API 1
        int pointerCount = ev.getHistorySize();
        Log.e(TAG, "applyHeaderPadding>>>>pointerCount==" + pointerCount);
        //for (int p = 0; p < pointerCount; p++) {
        if (mRefreshState == RELEASE_TO_REFRESH) {
            if (isVerticalFadingEdgeEnabled()) {
                setVerticalScrollBarEnabled(false);
            }

            //int historicalY = (int) ev.getHistoricalY(p);
            int historicalY = (int)ev.getY();
            // Calculate the padding to apply, we divide by 1.7 to
            // simulate a more resistant effect during pull.
            int topPadding = (int)(((historicalY - mLastMotionY) - mRefreshViewHeight) / 1.7);

            mRefreshView.setPadding(mRefreshView.getPaddingLeft(), topPadding,
                    mRefreshView.getPaddingRight(), mRefreshView
                            .getPaddingBottom());
        }
        //}
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setSelection(1);
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        super.setAdapter(adapter);

        setSelection(1);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // TODO Auto-generated method stub

    }

    private void measureView(View child) {
        ViewGroup.LayoutParams p = child.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
        int lpHeight = p.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,
                    MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = MeasureSpec.makeMeasureSpec(0,
                    MeasureSpec.UNSPECIFIED);
        }
        child.measure(childWidthSpec, childHeightSpec);
    }
}
