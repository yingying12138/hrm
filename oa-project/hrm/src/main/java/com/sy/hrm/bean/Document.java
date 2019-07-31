package com.sy.hrm.bean;

import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * Document 数据传输类
 * @author cisy
 * @email 1766181826@qq.com
 * @date 2019-07-29 19:43:43
 * @version 1.0
 */
@Alias("Document")
public class Document implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String title;
	private String url;
	private String remark;
	private java.util.Date createDate;
	private int userId;
	private User user;

	/** setter and getter method */
	public Document() {
	}

	public Document(int id, String title, String url, String remark, Date createDate, int userId, User user) {
		this.id = id;
		this.title = title;
		this.url = url;
		this.remark = remark;
		this.createDate = createDate;
		this.userId = userId;
		this.user = user;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}