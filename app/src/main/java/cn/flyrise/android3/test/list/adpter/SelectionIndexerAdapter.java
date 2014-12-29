/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-12-6 下午1:41:50
 */
package cn.flyrise.android3.test.list.adpter;

import cn.flyrise.android3.test.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AlphabetIndexer;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * 类功能描述：</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * 修改时间：</br>
 * 修改备注：</br>
 */
public class SelectionIndexerAdapter  extends BaseAdapter implements SectionIndexer {  
    private ArrayList<String> stringArray;  
    private Context context;  
    public SelectionIndexerAdapter(Context _context, ArrayList<String> arr) {  
        stringArray = arr;  
        context = _context;  
    }  
    public int getCount() {  
        return stringArray.size();  
    }  
    public Object getItem(int arg0) {  
        return stringArray.get(arg0);  
    }  
    public long getItemId(int arg0) {  
        return 0;  
    }  
    public View getView(int position, View v, ViewGroup parent) {  
        LayoutInflater inflate = ((Activity) context).getLayoutInflater();  
        View view = (View) inflate.inflate(R.layout.list_selection_indexer_item, null);  
        LinearLayout header = (LinearLayout) view.findViewById(R.id.section);  
        TextView sectionInfo = (TextView)view.findViewById(R.id.sectionInfo);
        String label = stringArray.get(position);  
        char firstChar = label.toUpperCase().charAt(0);  
        if (position == 0) {  
            setSection(sectionInfo, label);  
        } else {  
            String preLabel = stringArray.get(position - 1);  
            char preFirstChar = preLabel.toUpperCase().charAt(0);  
            if (firstChar != preFirstChar) {  
                setSection(sectionInfo, label);  
            } else {  
                header.setVisibility(View.GONE);  
            }  
        }  
        TextView textView = (TextView) view.findViewById(R.id.textView);  
        textView.setText(label);  
        return view;  
    }  
    private void setSection(TextView text, String label) {  
        text.setText(label.substring(0, 1).toUpperCase());
      
    }  
    public int getPositionForSection(int section) {  
        if (section == 0) {  
            return 0;  
        }  
        section += 64;//换成ABC的ASCI码
        for (int i = 0; i < stringArray.size(); i++) {  
            String l = stringArray.get(i);  
            char firstChar = l.toUpperCase().charAt(0);  
            if (firstChar == section) {  
                return i;  
            }  
        }  
        return -1;  
    }  
    public int getSectionForPosition(int arg0) {  
        return 0;  
    }  
    public Object[] getSections() {  
        return null;  
    }  
}  