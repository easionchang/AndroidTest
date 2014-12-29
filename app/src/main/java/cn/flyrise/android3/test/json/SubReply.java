package cn.flyrise.android3.test.json;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;


/**
*    
* 项目名称：Fe-Pt5.1(151)   
* 类名称：SubReply   
* 类描述：协同详情中的子回复类   
* 创建人：zms   
* 创建时间：Dec 19, 2012 3:15:13 PM   
* 修改人：zms   
* 修改时间：Dec 19, 2012 3:15:13 PM   
* 修改备注：   
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
