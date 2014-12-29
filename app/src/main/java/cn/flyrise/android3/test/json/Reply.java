package cn.flyrise.android3.test.json;

import java.util.ArrayList;
import java.util.List;

/**
*    
* ��Ŀ���ƣ�Fe-Pt5.1(151)   
* �����ƣ�Reply   
* ��������Эͬ�����еĻظ���   
* �����ˣ�zms   
* ����ʱ�䣺Dec 19, 2012 3:15:13 PM   
* �޸��ˣ�zms   
* �޸�ʱ�䣺Dec 19, 2012 3:15:13 PM   
* �޸ı�ע��   
* @version    
*
 */
public class Reply {
	public String no;
	public boolean isHideReply; //��ʶ��ǰ�ظ��Ƿ�Ҫ�����ӻظ��ͻظ�����
	
	private String id;
	private String nodeName;
	private String content;
	private String sendTime;
	private String sendUser;
	private String sendUserID;
	private String sendUserImageHref;
	private String attitude;
	private String isHidden;
	private String isTemporary;
	private String hasAttachment;
	private String hasReply;
	private String tips;
	private List<Attachment> attachments = new ArrayList<Attachment>();
	private List<SubReply> subReplies = new ArrayList<SubReply>();

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getNodeName() {
		return nodeName;
	}


	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}


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


	public String getSendUserID() {
		return sendUserID;
	}


	public void setSendUserID(String sendUserID) {
		this.sendUserID = sendUserID;
	}


	public String getSendUserImageHref() {
		return sendUserImageHref;
	}


	public void setSendUserImageHref(String sendUserImageHref) {
		this.sendUserImageHref = sendUserImageHref;
	}


	public String getAttitude() {
		return attitude;
	}


	public void setAttitude(String attitude) {
		this.attitude = attitude;
	}


	public String getIsHidden() {
		return isHidden;
	}


	public void setIsHidden(String isHidden) {
		this.isHidden = isHidden;
	}


	public String getIsTemporary() {
		return isTemporary;
	}


	public void setIsTemporary(String isTemporary) {
		this.isTemporary = isTemporary;
	}


	public String getHasAttachment() {
		return hasAttachment;
	}


	public void setHasAttachment(String hasAttachment) {
		this.hasAttachment = hasAttachment;
	}


	public String getHasReply() {
		return hasReply;
	}


	public void setHasReply(String hasReply) {
		this.hasReply = hasReply;
	}


	public String getTips() {
		return tips;
	}


	public void setTips(String tips) {
		this.tips = tips;
	}


	public List<Attachment> getAttachments() {
		return attachments;
	}


	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}


	public List<SubReply> getSubReplies() {
		return subReplies;
	}


	public void setSubReplies(List<SubReply> subReplies) {
		this.subReplies = subReplies;
	}


	public void setNo(String no) {
		this.no = no;
	}

}