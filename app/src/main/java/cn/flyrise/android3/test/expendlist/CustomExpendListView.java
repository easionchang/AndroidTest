/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2012-11-28 ����04:55:48
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
 * �๦��������</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * �޸�ʱ�䣺</br>
 * �޸ı�ע��</br>
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
            //��������ֱ�ӻ��Ƶ������У������Ǽ��뵽ViewGroup��
            drawChild(canvas, mHeaderView, getDrawingTime());
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {     
    }
    
    
    
    /**
     * ������Ҫ����������ʾ����
     * 1.���ȼ��㵱ǰӦ��ʾ������һ�����Group��
     * 2.�����ǰ������һ�����ڹ���������ʱ�̹���������
     * 3.�����һ���鲻�ڹ������͹̶�������
     */
    private void srollFloatingView(int firstVisibleItem,
            int visibleItemCount){
        int opGroupIndex = getOringinPointGroupIndex();
        if(opGroupIndex == -1){ //�����ǰ��ʾ�������-1�����账��
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
     * ����������Ϣ
     * ��ǰ������ʾ������һ�����
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
     * ���������̶���ͷ��
     */
    private void fixFloatingView(){
        if(mHeaderView.getTop() != 0){
            mHeaderView.layout(mHeaderView.getLeft(), 0, mHeaderView.getRight(), mHeaderView.getHeight());
        }
    }
    
    /**
     * 
     * ������Group������
     */
    private void scroll(int groupIndex,int firstVisibleItem){
        View v = getChildAt(groupIndex - firstVisibleItem);
        mHeaderView.layout(mHeaderView.getLeft(), v.getTop() - mHeaderView.getHeight(), mHeaderView.getRight(), v.getTop());//��Ч�����⣿����������������
    }
    
    
    /**
     * �ж�ĳ������Ƿ��ڹ�����
     * ����ڹ���������������Ҫ�ƶ�
     * ������Ҫ�ƶ�
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
     * �ж�ĳ��List�е�Item�Ƿ��ڹ�����
     * @param itemIndex
     * @param firstVisibleItem
     * @return
     */
    private boolean isInScrollArea(int itemIndex,int firstVisibleItem,int visibleItemCount){
        boolean itemVisiable = isVisiable(itemIndex, firstVisibleItem, visibleItemCount);
        if(!itemVisiable){  //�������Ŀ�����Ͳ��ɼ�����϶����ڹ�����
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
     * �ж�ĳ��Item�Ƿ�������ʾ
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
     * ��ȡGroup��ListView�е�λ��
     * @param groupIndex
     * @return
     */
    private int getFlatListPositionForGroup(int groupIndex){
        return getFlatListPosition(ExpandableListView.getPackedPositionForGroup(groupIndex));
    }
    
    /**
     * �ҳ�ԭ�㣨0,0������Ŀ������һ�����
     * �����ĸ�������������͸���ʾ�ĸ�������ʾ
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
