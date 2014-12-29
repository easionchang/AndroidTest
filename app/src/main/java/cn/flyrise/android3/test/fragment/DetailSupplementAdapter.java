//
//  CollaborationAddTextAdapter.java
//  FeOA
//
//  Created by song yingxin on 2011-12-8.
//  Copyright 2011 flyrise. All rights reserved.
//
package cn.flyrise.android3.test.fragment;

import cn.flyrise.android3.test.R;

import java.util.ArrayList;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.TextView;



public class DetailSupplementAdapter extends BaseAdapter {
	private Context mContext;
	private ArrayList<DetailSupplement> mlistViewMembers;
	

	public DetailSupplementAdapter(Context context) {
		this.mContext = context;
		mlistViewMembers = new ArrayList<DetailSupplement>();
	}

	@Override
	public int getCount() {
		return mlistViewMembers.size();
	}

	@Override
	public Object getItem(int index) {
		return mlistViewMembers.get(index);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	public void addListItemsMembers(DetailSupplement listViewMember) {
		mlistViewMembers.add(listViewMember);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.fragment_collaboration_supplycontent_listitem, null);
			ItemViewCache viewCache = new ItemViewCache();
			viewCache.mTimeView = (TextView) convertView.findViewById(R.id.time);
			viewCache.mUserView = (TextView) convertView.findViewById(R.id.sendUser);
			viewCache.mContentView = (TextView) convertView.findViewById(R.id.content);
			convertView.setTag(viewCache);
		}
		ItemViewCache cache = (ItemViewCache) convertView.getTag();
		// 设置文本和图片，然后返回这个View，用于ListView的Item的展�?
		//convertView.setBackgroundResource(colors[position % 2]);// 设置隔行换色
		cache.mTimeView.setText(mlistViewMembers.get(position).getTime());
		cache.mUserView.setText(mlistViewMembers.get(position).getSenduser());
		cache.mContentView.setText(mlistViewMembers.get(position).getContent());
		//cache.mAttachmentView.setVisibility(mlistViewMembers.get(position).hasAttachment() ? View.VISIBLE : View.INVISIBLE);
		return convertView;
	}

	// 元素的缓冲类,用于优化ListView
	private static class ItemViewCache {
		public TextView mTimeView;
		public TextView mUserView;
		public TextView mContentView;
		//public TextView mAttachmentView;
	}
}
