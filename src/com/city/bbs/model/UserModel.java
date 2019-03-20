package com.city.bbs.model;

import java.sql.Blob;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class UserModel {
	@Id
	@Column(name="u_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int u_id;
	@Basic
	@Column(name="username")
	private String username;
	@Column(name="password")
	private String password;
	@Column(name="gender")
	private String gender;
	@Column(name="faculty")
	private String faculty;
	@Column(name="email")
	private String email;
	@Column(name="disc")
	private String disc;
	@Column(name="regTime")
	private Date regTime;
	@Column(name="role")
	private int role;
	@Column(name="photo")
	private Blob photo;
	@Column(name="level")
	private int level;
	@OneToMany(mappedBy="user")
	private Set<PostModel> posts=null;

	@OneToMany(mappedBy="user")
	private Set<ReplyModel> replies=null;
	
	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDisc() {
		return disc;
	}

	public void setDisc(String disc) {
		this.disc = disc;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public Blob getPhoto() {
		return photo;
	}

	public void setPhoto(Blob photo) {
		this.photo = photo;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Set<PostModel> getPosts() {
		return posts;
	}

	public void setPosts(Set<PostModel> posts) {
		this.posts = posts;
	}

	public Set<ReplyModel> getReplies() {
		return replies;
	}

	public void setReplies(Set<ReplyModel> replies) {
		this.replies = replies;
	}
	
	
}
