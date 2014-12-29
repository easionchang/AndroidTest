/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2012-10-27 上午09:53:53
 */

package cn.flyrise.android3.test.viewpager;

import cn.flyrise.android3.test.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

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
public class PagerTitleDemoActivity extends Activity {

    List<View> viewList;
    List<String> tileList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager);
        ViewPager pager = (ViewPager)findViewById(R.id.viewpager);
        PagerTitleStrip pagerTitle = (PagerTitleStrip)findViewById(R.id.pagertitle);

        LayoutInflater inflater = LayoutInflater.from(this);
        View view1 = inflater.inflate(R.layout.main, null);
        View view2 = inflater.inflate(R.layout.test_event_handle, null);

        viewList = new ArrayList<View>();
        viewList.add(view1);
        viewList.add(view2);

        tileList = new ArrayList<String>();
        tileList.add("view1");
        tileList.add("view2");

        PagerAdapter pageAdapter = new PagerAdapter() {

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                return viewList.size();
            }

            public Object instantiateItem(View container, int position) {
                ((ViewPager)container).addView(viewList.get(position));

                return viewList.get(position);
            }

            public CharSequence getPageTitle(int position) {
                return tileList.get(position);
            }

            public void destroyItem(View container, int position, Object object) {
                ((ViewPager)container).removeView(viewList.get(position));
            }


            
            

        };

        pager.setAdapter(pageAdapter);
    }
}
