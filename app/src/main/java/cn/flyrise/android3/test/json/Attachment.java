package cn.flyrise.android3.test.json;

import com.google.gson.annotations.Expose;

/**
*    
* ��Ŀ���ƣ�Fe-Pt5.1(151)   
* �����ƣ�Attachment   
* ��������Эͬ�����еĸ�����   
* �����ˣ�zms   
* ����ʱ�䣺Dec 19, 2012 3:15:13 PM   
* �޸��ˣ�zms   
* �޸�ʱ�䣺Dec 19, 2012 3:15:13 PM   
* �޸ı�ע��   
* @version    
*
 */
public class Attachment{
    @Expose
	private String id;
	
	@Expose
	private String name;
	@Expose
	private String type;
	@Expose
	private String size;
	@Expose
	private String href;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
