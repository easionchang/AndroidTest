package cn.flyrise.android3.test.json;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;


/**
*    
* ��Ŀ���ƣ�Fe-Pt5.1(151)   
* �����ƣ�SubReply   
* ��������Эͬ�����е��ӻظ���   
* �����ˣ�zms   
* ����ʱ�䣺Dec 19, 2012 3:15:13 PM   
* �޸��ˣ�zms   
* �޸�ʱ�䣺Dec 19, 2012 3:15:13 PM   
* �޸ı�ע��   
* @version    
*
 */
public class SubReply{
	public String id;
	
	private String content;
	
	
	private String sendTime;
	
	
	private String sendUser;
	
	
	private List<Attachment> attachments = new ArrayList<Attachment>();;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getSendUser() {
		return sendUser;
	}
	public void setSendUser(String sendUser) {
		this.sendUser = sendUser;
	}
	public List<Attachment> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}
}
