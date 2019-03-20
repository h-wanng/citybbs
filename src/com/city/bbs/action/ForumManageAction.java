package com.city.bbs.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.city.bbs.model.ForumModel;
import com.city.bbs.service.IForumService;

@SuppressWarnings("serial")
@Controller
public class ForumManageAction extends BaseAction {
	private IForumService forumService = null;
	
	@Autowired
	public void setForumService(IForumService forumService) {
		this.forumService = forumService;
	}
	 
	private ForumModel forum;
	
	
	public ForumModel getForum() {
		return forum;
	}


	public void setForum(ForumModel forum) {
		this.forum = forum;
	}


	public void forumDelete() {
		try {
			forumService.deleteForum(forum);
			this.write("success","删除成功");
		} catch (Exception e) {
			this.write("error","删除失败");
			e.printStackTrace();
		}
	}
	
	public void forumUpdate() {
		try {
			forumService.updateForum(forum);
			this.write("success","修改成功");
		} catch (Exception e) {
			this.write("error","修改失败");
			e.printStackTrace();
		}
	}
	
	public void forumAdd() {
		try {
			forumService.addForum(forum);
			this.write("success","增加成功");
		} catch (Exception e) {
			this.write("error","增加失败");
			e.printStackTrace();
		}
	}
}
