package com.city.bbs.service;

import java.util.List;

import com.city.bbs.model.ForumModel;
import com.city.bbs.model.PartModel;


public interface IForumService {
	public void addForum(ForumModel fm) throws Exception;

	public void updateForum(ForumModel fm) throws Exception;

	public ForumModel getForumById(int id) throws Exception;

	public void deleteForum(ForumModel fm) throws Exception;

	public List<ForumModel> getAllForumList() throws Exception;

	public List<ForumModel> getForumByPage(int page, int rows) throws Exception;

	public int getForumCount() throws Exception;

	public ForumModel getForum(ForumModel fm) throws Exception;
	
	public List<PartModel> getPartsByForum(int forumId) throws Exception;
}
