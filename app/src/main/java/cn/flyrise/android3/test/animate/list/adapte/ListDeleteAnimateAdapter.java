/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-11-7 下午3:05:22
 */
package cn.flyrise.android3.test.animate.list.adapte;

import cn.flyrise.android3.test.R;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类功能描述：</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * 修改时间：</br>
 * 修改备注：</br>
 */
public class ListDeleteAnimateAdapter extends BaseAdapter{
    private Context mContext;
    List<Data> data = new ArrayList<Data>();

    private Map<View,Integer> viewMap = new HashMap<View,Integer>();
    
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_1, null);
            ViewHolder viewTag = new ViewHolder();
            TextView textView = (TextView)convertView.findViewById(R.id.key);
            viewTag.textView = textView;
            convertView.setTag(viewTag);
        }
        ViewHolder viewTag = (ViewHolder)convertView.getTag();
        viewTag.textView.setText(data.get(position).getInfo());
        
        viewMap.put(convertView, position);
        return convertView;
    }
    
    public void animateItem(int index,long id){
        View view = getAnimateView(index);
        createAnimator(view,index,id);
    }
    
    private void createAnimator(final View view,final int index,final long id){
        final ViewGroup.LayoutParams lp = view.getLayoutParams();
        final int originalHeight = view.getHeight();

        ValueAnimator animator = ValueAnimator.ofInt(originalHeight, 0);
        animator.addListener(new AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                lp.height = 0;
                view.setLayoutParams(lp);
                data.remove(new Data(id));
                ListDeleteAnimateAdapter.this.notifyDataSetChanged();
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }
        });

        animator.addUpdateListener(new AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                lp.height = (Integer) valueAnimator.getAnimatedValue();
                view.setLayoutParams(lp);
            }
        });
        animator.start();
    }
    
    private View getAnimateView(int index){
       for (View view: viewMap.keySet()) {
           if(viewMap.get(view) == index){
               return view;
           }
       }
       return null;
    }
    
    public void onAnimationEnd(){
        
    }

    class ViewHolder{
        TextView textView;
    }
    
    
    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public ListDeleteAnimateAdapter(Context context,List<Data> data){
        mContext = context;
        this.data = data;
    }
}
