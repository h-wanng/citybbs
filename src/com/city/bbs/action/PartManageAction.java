package com.city.bbs.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.city.bbs.model.ForumModel;
import com.city.bbs.model.PartModel;
import com.city.bbs.service.IForumService;
import com.city.bbs.service.IPartService;

@SuppressWarnings("serial")
@Controller
public class PartManageAction extends BaseAction {
	private IPartService partService = null;
	private IForumService forumService = null;
	
	@Autowired
	public void setPartService(IPartService partService) {
		this.partService = partService;
	}
	
	@Autowired
	public void setForumService(IForumService forumService) {
		this.forumService = forumService;
	}

	private PartModel part;
	private int fid;

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public PartModel getPart() {
		return part;
	}

	public void setPart(PartModel part) {
		this.part = part;
	}
	
	public void partAdd() {
		try {
			ForumModel forum = forumService.getForumById(fid);
			part.setForum(forum);
			partService.addPart(part);
			this.write("success", "添加成功");
		} catch (Exception e) {
			this.write("error", "添加失败");
			e.printStackTrace();
		}
	}
	
	public void partDelete() {
		try {
			partService.deletePart(part);
			this.write("success", "删除成功");
		} catch (Exception e) {
			this.write("error", "删除失败");
			e.printStackTrace(); 
		}
	}
	
	public void partUpdate() {
		System.out.println("fid  "+fid);
		try {
			ForumModel forum = forumService.getForumById(fid);
			part.setForum(forum);
			partService.updatePart(part);
			this.write("success", "修改成功");
		} catch (Exception e) {
			this.write("error", "修改失败");
			e.printStackTrace();
		}
	}
}
