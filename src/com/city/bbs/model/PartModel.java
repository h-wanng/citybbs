package com.city.bbs.model;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="parts")
public class PartModel {
	@Id
	@Column(name="p_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int p_id;
	@Basic
	@Column(name="title")
	private String title;
	@Column(name="manager")
	private int manager;
	
	@ManyToOne
	@JoinColumn(name="forum_id")
	private ForumModel forum=null;
	
	@OneToMany(mappedBy="part")
	private Set<PostModel> posts=null;

	public int getP_id() {
		return p_id;
	}

	public void setP_id(int p_id) {
		this.p_id = p_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getManager() {
		return manager;
	}

	public void setManager(int manager) {
		this.manager = manager;
	}

	public ForumModel getForum() {
		return forum;
	}

	public void setForum(ForumModel forum) {
		this.forum = forum;
	}

	public Set<PostModel> getPosts() {
		return posts;
	}

	public void setPosts(Set<PostModel> posts) {
		this.posts = posts;
	}
	
	
}
