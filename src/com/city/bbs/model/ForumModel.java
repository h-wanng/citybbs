package com.city.bbs.model;

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
@Table(name="forums")
public class ForumModel {
	@Id
	@Column(name="f_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int f_id;
	@Basic
	@Column(name="title")
	private String title;
	
	@OneToMany(mappedBy="forum")
	private Set<PartModel> parts=null;

	public int getF_id() {
		return f_id;
	}

	public void setF_id(int f_id) {
		this.f_id = f_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<PartModel> getParts() {
		return parts;
	}

	public void setParts(Set<PartModel> parts) {
		this.parts = parts;
	}
	
	
}
