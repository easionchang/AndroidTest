/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-12-6 下午1:41:50
 */
package cn.flyrise.android3.test.list.adpter;

import cn.flyrise.android3.test.R;
import cn.flyrise.android3.test.list.vo.Contact;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.SectionIndexer;
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
public class AlphabetIndexerAdapter  extends BaseAdapter {
    private Context mContext;
    private List<Contact> mContactList;
    private SectionIndexer indexer;


    public AlphabetIndexerAdapter(Context context,List<Contact> contactList){
        mContext = context;
        mContactList = contactList;
    }
    
    @Override
    public int getCount() {
        return mContactList.size();
    }

    @Override
    public Object getItem(int position) {
        return mContactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_selection_indexer_item, null);
            LinearLayout section = (LinearLayout)convertView.findViewById(R.id.section);
            TextView sectionInfo = (TextView)convertView.findViewById(R.id.sectionInfo);
            TextView content = (TextView)convertView.findViewById(R.id.textView);
            
            ViewTag tag = new ViewTag();
            tag.content = content;
            tag.section = section;
            tag.sectionInfo = sectionInfo;
            convertView.setTag(tag);
        }
        
        ViewTag tag = (ViewTag)convertView.getTag();
        determineSection(position, tag.sectionInfo);
        tag.content.setText(mContactList.get(position).getName());
        return convertView;
    }  
    
    private void determineSection(int position,TextView header){
        header.setVisibility(View.GONE);
        int section = indexer.getSectionForPosition(position);
        if(position == indexer.getPositionForSection(section)){
            setSection(header,mContactList.get(position).getSortKey());
        }
    }
    
    private void setSection(TextView header, String label) { 
        header.setVisibility(View.VISIBLE);
        header.setText(label.substring(0, 1).toUpperCase());  
    }  
    
    class ViewTag{
        LinearLayout section;
        TextView content;
        TextView sectionInfo;
    }
   

    public SectionIndexer getIndexer() {
        return indexer;
    }

    public void setIndexer(SectionIndexer indexer) {
        this.indexer = indexer;
    }
}  