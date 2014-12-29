/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-12-6 ����1:39:02
 */
package cn.flyrise.android3.test.list;

import cn.flyrise.android3.test.R;
import cn.flyrise.android3.test.list.adpter.AlphabetIndexerAdapter;
import cn.flyrise.android3.test.list.view.SideBar;
import cn.flyrise.android3.test.list.vo.Contact;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AlphabetIndexer;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * �๦��������</br>
 *
 * @author zms
 * @version 1.0
 * </p>
 * �޸�ʱ�䣺</br>
 * �޸ı�ע��</br>
 */
public class TestAlphabetIndexerActivity extends Activity{
    
    
    
    AlphabetIndexer alphabetIndexer;
    TextView title;
    LinearLayout titleLayout;
    private String alphabet = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /**
     * �ϴε�һ���ɼ�Ԫ�أ����ڹ���ʱ��¼��ʶ��
     */
    private int lastFirstVisibleItem = -1;
    /** Called when the activity is first created. */  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.list_selection_indexer);  
        ListView listView = (ListView)findViewById(R.id.myListView);
        title = (TextView)findViewById(R.id.sectionInfo);
        title.setVisibility(View.VISIBLE);
        titleLayout = (LinearLayout)findViewById(R.id.section);
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri, new String[] { "display_name", "sort_key" }, null, null, "sort_key");
        List<Contact> contactList = buildContact(cursor);
        alphabetIndexer = new AlphabetIndexer(cursor, 1, alphabet);
       
        AlphabetIndexerAdapter adapter = new AlphabetIndexerAdapter(this, contactList);
        adapter.setIndexer(alphabetIndexer);
        
        listView.setAdapter(adapter);
        SideBar sideBar = (SideBar)findViewById(R.id.sideBar);
        sideBar.setListView(listView,alphabetIndexer);
        //listView.setFastScrollEnabled(true);
        //listView.setFastScrollAlwaysVisible(true);
        
        listView.setOnScrollListener(new OnScrollListener() {
            
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                    int visibleItemCount, int totalItemCount) {
                //����������ֱ���ס�����
                int section = alphabetIndexer.getSectionForPosition(firstVisibleItem);
                int nextSecPosition = alphabetIndexer.getPositionForSection(section + 1);
                
                //��һ����ʾ��Ԫ���л�ʱ�����ȱ�֤��ʾ����ʾ��ͷ��
                if (firstVisibleItem != lastFirstVisibleItem) {
                    MarginLayoutParams params = (MarginLayoutParams) titleLayout.getLayoutParams();
                    params.topMargin = 0;
                    titleLayout.setLayoutParams(params);
                    title.setText(String.valueOf(alphabet.charAt(section)));
                }
                
                //�����ʾ�������ƽ��Ƴ���֤����
                if (nextSecPosition == firstVisibleItem + 1) {
                    View childView = view.getChildAt(0);
                    if (childView != null) {
                        int titleHeight = titleLayout.getHeight();
                        int bottom = childView.getBottom();
                        MarginLayoutParams params = (MarginLayoutParams) titleLayout
                                .getLayoutParams();
                        if (bottom < titleHeight) { //�����ʾ�����ڵ����򲻹��������������������һ��Ԫ�صĵױ߽��ƽ��Ƴ�
                            float pushedDistance = bottom - titleHeight;
                            params.topMargin = (int) pushedDistance;
                            titleLayout.setLayoutParams(params);
                        } else {                    //�����ʾ�����ڵ������㹻����̶�
                            if (params.topMargin != 0) {
                                params.topMargin = 0;
                                titleLayout.setLayoutParams(params);
                            }
                        }
                    }
                }
                lastFirstVisibleItem = firstVisibleItem;
                
            }
        });
    }  
    
    private List<Contact> buildContact(Cursor cursor){
        List<Contact> contacts = new ArrayList<Contact>();
        while(cursor.moveToNext()){
            String name = cursor.getString(0);
            String sortKey = cursor.getString(1);
            Contact c = new Contact();
            c.setName(name);
            c.setSortKey(getSortKey(sortKey));
            contacts.add(c);
            //Log.e("Test", "name====="+name+"    sortKey======"+c.getSortKey());
        }
        return contacts;
    }
    
    /**
     * ��ȡsort key���׸��ַ��������Ӣ����ĸ��ֱ�ӷ��أ����򷵻�#��
     * 
     * @param sortKeyString
     *            ���ݿ��ж�ȡ����sort key
     * @return Ӣ����ĸ����#
     */
    private String getSortKey(String sortKeyString) {
        String key = sortKeyString.substring(0, 1).toUpperCase();
        if (key.matches("[A-Z]")) {
            return key;
        }
        return "#";
    }
  
}
