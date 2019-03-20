package com.city.bbs.model;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="hobby")
public class HobbyModel {
	@Id
	@Column(name="h_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int h_id;
	@Basic
	@Column(name="name")
	private String name;
	
	@ManyToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
	private Set<UserModel> users;

	public int getH_id() {
		return h_id;
	}

	public void setH_id(int h_id) {
		this.h_id = h_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<UserModel> getUsers() {
		return users;
	}

	public void setUsers(Set<UserModel> users) {
		this.users = users;
	}
	
	
}
