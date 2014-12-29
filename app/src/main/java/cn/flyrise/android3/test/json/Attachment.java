package cn.flyrise.android3.test.json;

import com.google.gson.annotations.Expose;

/**
*    
* 项目名称：Fe-Pt5.1(151)   
* 类名称：Attachment   
* 类描述：协同详情中的附件类   
* 创建人：zms   
* 创建时间：Dec 19, 2012 3:15:13 PM   
* 修改人：zms   
* 修改时间：Dec 19, 2012 3:15:13 PM   
* 修改备注：   
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
