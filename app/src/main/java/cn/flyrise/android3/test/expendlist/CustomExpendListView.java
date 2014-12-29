/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2012-11-28 下午04:55:48
 */
package cn.flyrise.android3.test.expendlist;

import cn.flyrise.android3.test.R;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
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
public class CustomExpendListView extends ExpandableListView implements OnScrollListener{
    private int currGroup  = 0;
    private View mHeaderView;
    private IGroupInfoChangedListener listener;

    public CustomExpendListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        registerListener();
    }

    public CustomExpendListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        registerListener();
    }

    public CustomExpendListView(Context context) {
        super(context);
        registerListener();
    }

    private void registerListener() {
        setOnScrollListener(this);
        //setOnGroupClickListener(this);
    }
    TextView tv ;
    public void setHeaderView(View view) {
        mHeaderView = view;
         tv = (TextView)mHeaderView.findViewById(R.id.tv);
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
        ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);

        if (mHeaderView != null) {
            setFadingEdgeLength(0);
        }

        requestLayout();
    }
    
    private int mHeaderViewWidth;
    private int mHeaderViewHeight;
    
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mHeaderView != null) {
            measureChild(mHeaderView, widthMeasureSpec, heightMeasureSpec);
            mHeaderViewWidth = mHeaderView.getMeasuredWidth();
            mHeaderViewHeight = mHeaderView.getMeasuredHeight();
        }
    }
    
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // TODO Auto-generated method stub
        super.onLayout(changed, l, t, r, b);
        if(mHeaderView != null){
            mHeaderView.layout(0, 0, mHeaderViewWidth, mHeaderViewHeight);
            Log.e(">>", "==========mHeaderViewWidth=="+mHeaderViewWidth+"=======mHeaderViewHeight=="+mHeaderViewHeight+"=======================");
        }
        
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
            int visibleItemCount, int totalItemCount) {
        if(mHeaderView != null){
            srollFloatingView(firstVisibleItem,visibleItemCount);
            Log.e(">>", "==========mHeaderViewWidth=="+mHeaderViewWidth+"=======mHeaderViewHeight=="+mHeaderViewHeight+"=======================");
        }
       
    }
    
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (mHeaderView != null) {
            //分组栏是直接绘制到界面中，而不是加入到ViewGroup中
            drawChild(canvas, mHeaderView, getDrawingTime());
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {     
    }
    
    
    
    /**
     * 根据需要滚动悬浮提示区域
     * 1.首先计算当前应显示的是哪一个组别（Group）
     * 2.如果当前组别的下一个组在滚动区，则时刻滚动悬浮条
     * 3.如果下一个组不在滚动区就固定悬浮条
     */
    private void srollFloatingView(int firstVisibleItem,
            int visibleItemCount){
        int opGroupIndex = getOringinPointGroupIndex();
        if(opGroupIndex == -1){ //如果当前显示的组别是-1则无需处理
            return;
        }
        
        changeFloatingInfo(opGroupIndex); 
        
        int nextGroupIndex = currGroup + 1;
        boolean needScroll = isGroupInScrollArea(nextGroupIndex,firstVisibleItem, visibleItemCount);
        if(needScroll){
            scroll(getFlatListPositionForGroup(nextGroupIndex), firstVisibleItem);
        }else{
            fixFloatingView();
        }
    }
    
    /**
     * 设置悬浮信息
     * 当前悬浮显示的是哪一个组别
     * @param opGroupInde
     */
    private void changeFloatingInfo(int groupIndex){
        if(currGroup == groupIndex){
            return;
        }
       
        currGroup = groupIndex;
        if(listener != null){
            listener.groupChanged(groupIndex);
        }
    }
    
    /**
     * 将悬浮区固定在头部
     */
    private void fixFloatingView(){
        if(mHeaderView.getTop() != 0){
            mHeaderView.layout(mHeaderView.getLeft(), 0, mHeaderView.getRight(), mHeaderView.getHeight());
        }
    }
    
    /**
     * 
     * 悬浮在Group的上面
     */
    private void scroll(int groupIndex,int firstVisibleItem){
        View v = getChildAt(groupIndex - firstVisibleItem);
        mHeaderView.layout(mHeaderView.getLeft(), v.getTop() - mHeaderView.getHeight(), mHeaderView.getRight(), v.getTop());//有效率问题？？？？？？？？？
    }
    
    
    /**
     * 判断某个组别是否在滚动区
     * 如果在滚动区则悬浮条需要移动
     * 否则不需要移动
     * @param firstVisibleItem
     * @param visibleItemCount
     * @return
     */
    private boolean isGroupInScrollArea(int groupIndex,int firstVisibleItem,int visibleItemCount){
        int nextGroupIndex = getFlatListPositionForGroup(groupIndex);
        boolean nextGroupInScrollArea = isInScrollArea(nextGroupIndex, firstVisibleItem,visibleItemCount) ;
        if(nextGroupInScrollArea){
             return true;
        }else{
            return false;
        }
    }
    
    
    /**
     * 判断某个List中的Item是否在滚动区
     * @param itemIndex
     * @param firstVisibleItem
     * @return
     */
    private boolean isInScrollArea(int itemIndex,int firstVisibleItem,int visibleItemCount){
        boolean itemVisiable = isVisiable(itemIndex, firstVisibleItem, visibleItemCount);
        if(!itemVisiable){  //如果该条目根本就不可见，则肯定不在滚动区
            return false;
        }
        
        View v = getChildAt(itemIndex - firstVisibleItem);
        if(v.getTop() >= 0 && v.getTop() <= mHeaderView.getHeight()){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * 判读某个Item是否正在显示
     * @param listIndex
     * @return
     */
    private boolean isVisiable(int itemIndex,int firstVisibleItem,int visibleItemCount){
        if(itemIndex < visibleItemCount + firstVisibleItem && itemIndex >= firstVisibleItem){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * 获取Group在ListView中的位置
     * @param groupIndex
     * @return
     */
    private int getFlatListPositionForGroup(int groupIndex){
        return getFlatListPosition(ExpandableListView.getPackedPositionForGroup(groupIndex));
    }
    
    /**
     * 找出原点（0,0）的条目属于哪一个组别
     * 属于哪个组别，则悬浮条就该显示哪个组别的提示
     * @return
     */
    private int getOringinPointGroupIndex(){
        int originItemIndex = pointToPosition(0, 0);
        long packegeIndex = getExpandableListPosition(originItemIndex);
        int groupIndex = ExpandableListView.getPackedPositionGroup(packegeIndex);
        return groupIndex;
    }
    
    interface IGroupInfoChangedListener{
        public void groupChanged(int groupIndex);
    }
    
    /**
     * @return the listener
     */
    public IGroupInfoChangedListener getListener() {
        return listener;
    }

    /**
     * @param listener the listener to set
     */
    public void setListener(IGroupInfoChangedListener listener) {
        this.listener = listener;
    }
}
