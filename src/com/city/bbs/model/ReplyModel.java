package com.city.bbs.model;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="replies")
public class ReplyModel {
	@Id
	@Column(name="r_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int r_id;
	@Basic
	@Column(name="content")
	private String content;
	@Column(name="replyTime")
	private Date replyTime;
	
	@ManyToOne
	@JoinColumn(name="post_id")
	private PostModel post=null;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserModel user=null;

	public int getR_id() {
		return r_id;
	}

	public void setR_id(int r_id) {
		this.r_id = r_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

	public PostModel getPost() {
		return post;
	}

	public void setPost(PostModel post) {
		this.post = post;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}
	
	
}
