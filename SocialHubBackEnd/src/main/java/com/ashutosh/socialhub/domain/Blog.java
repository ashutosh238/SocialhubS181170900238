package com.ashutosh.socialhub.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table
public class Blog {
	
	@Id
	private int blogId;
	private String blogname;
	private String blogcontent;
	private Date createdate;
	private String loginname;
	private String status;
	private int likes;
	private int dislikes;
	public int getBlogId() {
		return blogId;
	}
	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}
	public String getBolgName() {
		return blogname;
	}
	public void setBolgName(String blogname) {
		this.blogname = blogname;
	}
	public String getBolgContent() {
		return blogcontent;
	}
	public void setBolgContent(String bolgContent) {
		this.blogcontent = bolgContent;
	}
	public Date getCreateDate() {
		return createdate;
	}
	public void setCreateDate(Date createDate) {
		this.createdate = createDate;
	}
	public String getLoginName() {
		return loginname;
	}
	public void setLoginName(String loginName) {
		this.loginname = loginName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getDislikes() {
		return dislikes;
	}
	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}
	

}
