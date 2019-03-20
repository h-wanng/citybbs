package com.city.bbs.model;

import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="posts")
public class PostModel {
	@Id
	@Column(name="po_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int po_id;
	
	@Basic
	@Column(name="title")
	private String title;
	@Column(name="content")
	private String content;
	@Column(name="createTime")
	private Date createTime;
	@Column(name="clickNum")
	private int clickNum;
	
	@ManyToOne
	@JoinColumn(name="part_id")
	private PartModel part=null;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserModel user=null;
	
	@OneToMany(mappedBy="post", fetch=FetchType.EAGER)
	private Set<ReplyModel> replies=null;
	
	public int getPo_id() {
		return po_id;
	}

	public void setPo_id(int po_id) {
		this.po_id = po_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getClickNum() {
		return clickNum;
	}

	public void setClickNum(int clickNum) {
		this.clickNum = clickNum;
	}

	public PartModel getPart() {
		return part;
	}

	public void setPart(PartModel part) {
		this.part = part;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public Set<ReplyModel> getReplies() {
		return replies;
	}

	public void setReplies(Set<ReplyModel> replies) {
		this.replies = replies;
	}
	
	
}
