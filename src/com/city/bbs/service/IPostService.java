package com.city.bbs.service;

import java.util.List;

import com.city.bbs.model.PostModel;


public interface IPostService {
	public void addPost(PostModel pom) throws Exception;

	public void updatePost(PostModel pom) throws Exception;

	public PostModel getPostById(int id) throws Exception;

	public void deletePost(PostModel pom) throws Exception;

	public List<PostModel> getAllPostList() throws Exception;

	public List<PostModel> getPostsByPart(int partId) throws Exception;

	public List<PostModel> getPostsByPage(int page, int rows) throws Exception;

	public int getCount() throws Exception;

	public PostModel getPost(PostModel pom) throws Exception;

	public int getCount(int partId) throws Exception;

	public List<PostModel> getByPage(int page, int rows, int partId) throws Exception;

	public void clickPost(int id) throws Exception;

	public void deletePostsByPart(int partId) throws Exception;
}
