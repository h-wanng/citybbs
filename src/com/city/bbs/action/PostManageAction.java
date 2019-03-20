package com.city.bbs.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.city.bbs.model.PostModel;
import com.city.bbs.service.IPostService;

@SuppressWarnings("serial")
@Controller
public class PostManageAction extends BaseAction {
	private IPostService postService = null;

	@Autowired
	public void setPostService(IPostService postService) {
		this.postService = postService;
	}
	
	private PostModel post;

	public PostModel getPost() {
		return post;
	}

	public void setPost(PostModel post) {
		this.post = post;
	}
	
	public void postDelete() {
		try {
			postService.deletePost(post);
			this.write("success", "删除成功");
		} catch (Exception e) {
			this.write("error", "删除失败");
			e.printStackTrace();
		}
	}
}
