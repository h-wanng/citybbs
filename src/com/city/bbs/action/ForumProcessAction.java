package com.city.bbs.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.city.bbs.model.ForumModel;
import com.city.bbs.service.IForumService;
import com.city.bbs.service.IPartService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Controller
public class ForumProcessAction extends ActionSupport {
	private IForumService forumService = null;
	private IPartService partService = null;

	@Autowired
	public void setForumService(IForumService forumService) {
		this.forumService = forumService;
	}
	@Autowired
	public void setPartService(IPartService partService) {
		this.partService = partService;
	}
	private List<ForumModel> forumList;
	private Map<Integer, Integer> pCount;
	
	public Map<Integer, Integer> getpCount() {
		return pCount;
	}
	public void setpCount(Map<Integer, Integer> pCount) {
		this.pCount = pCount;
	}
	public List<ForumModel> getForumList() {
		return forumList;
	}


	public void setForumList(List<ForumModel> forumList) {
		this.forumList = forumList;
	}

	public String getAllForums() throws Exception {
		forumList = forumService.getAllForumList();
		pCount = new HashMap<Integer, Integer>();
		for (ForumModel forum : forumList) {
			int count = partService.getPartCountByForum(forum.getF_id());
			pCount.put(forum.getF_id(), count);
		}
		return SUCCESS;
	}
	
}
