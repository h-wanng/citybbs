  package com.city.bbs.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.city.bbs.model.PartModel;
import com.city.bbs.service.IPartService;
import com.city.bbs.service.IPostService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Controller
public class PartProcessAction extends ActionSupport {
	private IPartService partService = null;
	private IPostService postService = null;
	@Autowired
	public void setPartService(IPartService partService) {
		this.partService = partService;
	}

	@Autowired
	public void setPostService(IPostService postService) {
		this.postService = postService;
	}


	private List<PartModel> partList;
	private Map<Integer, Integer> poCount;
	public Map<Integer, Integer> getPoCount() {
		return poCount;
	}

	public void setPoCount(Map<Integer, Integer> poCount) {
		this.poCount = poCount;
	}


	private int forumId;
	public int getForumId() {
		return forumId;
	}


	public void setForumId(int forumId) {
		this.forumId = forumId;
	}


	public List<PartModel> getPartList() {
		return partList;
	}


	public void setPartList(List<PartModel> partList) {
		this.partList = partList;
	}
	
	public String getAllParts() throws Exception {
		partList = partService.getAllPartListByForum(forumId);
		poCount = new HashMap<Integer, Integer>();
		for (PartModel part : partList) {
			int count = postService.getCount(part.getP_id());
			poCount.put(part.getP_id(), count);
		}
		return SUCCESS;
	}
}
