package com.city.bbs.service;

import java.util.List;

import com.city.bbs.model.PartModel;

public interface IPartService {
	public void addPart(PartModel pm) throws Exception;

	public void updatePart(PartModel pm) throws Exception;

	public PartModel getPartById(int id) throws Exception;

	public void deletePart(PartModel pm) throws Exception;

	public List<PartModel> getAllPartList() throws Exception;
	
	public List<PartModel> getAllPartListByForum(int forumId) throws Exception;

	public List<PartModel> getPartByPage(int page, int rows) throws Exception;

	public int getPartCountByForum(int forumId) throws Exception;

	public PartModel getPart(PartModel pm) throws Exception;
	
	public int getPartCount() throws Exception;
}
