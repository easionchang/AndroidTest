//
//  DetailSupplement.java
//  FeOA
//
//  Created by lin yiqi on 2012-1-11.
//  Copyright 2012 flyrise. All rights reserved.
//
package cn.flyrise.android3.test.fragment;

/**
 * @author <a href="mailto:184618345@qq.com">017</a>
 */
public class DetailSupplement {
	private String senduser;
	private String time;
	private String content;
	private boolean attachment;

	/**
	 * @param time 时间
	 * @param content 补充内容
	 * @param attachment 是否有附�?
	 */
	public DetailSupplement(String senduser, String time, String content, boolean attachment) {
		this.senduser = senduser;
		this.time = time;
		this.content = content;
		this.attachment = attachment;
	}

	public String getSenduser() {
		return senduser;
	}

	public void setSenduser(String senduser) {
		this.senduser = senduser;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean hasAttachment() {
		return attachment;
	}

	public void setAttachment(boolean attachment) {
		this.attachment = attachment;
	}
}
