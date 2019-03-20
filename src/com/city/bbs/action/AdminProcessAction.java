package com.city.bbs.action;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.city.bbs.model.ForumModel;
import com.city.bbs.model.PartModel;
import com.city.bbs.model.PostModel;
import com.city.bbs.model.UserModel;
import com.city.bbs.service.IForumService;
import com.city.bbs.service.IPartService;
import com.city.bbs.service.IPostService;
import com.city.bbs.service.IUserService;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller
public class AdminProcessAction extends BaseAction {
	private IUserService userService = null;
	private IForumService forumService = null;
	private IPartService partService = null;
	private IPostService postService = null;
	
	private int rows = 6;
	private int page = 1;
	private int pageCount;
	private List<UserModel> usersList;
	private List<ForumModel> forumList;
	private List<PartModel> partList;
	private List<PostModel> postList;
	@Autowired
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	@Autowired
	public void setForumService(IForumService forumService) {
		this.forumService = forumService;
	}
	@Autowired
	public void setPartService(IPartService partService) {
		this.partService = partService;
	}
	@Autowired
	public void setPostService(IPostService postService) {
		this.postService = postService;
	}
	public List<UserModel> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<UserModel> usersList) {
		this.usersList = usersList;
	}

	public List<ForumModel> getForumList() {
		return forumList;
	}
	public void setForumList(List<ForumModel> forumList) {
		this.forumList = forumList;
	}
	public List<PartModel> getPartList() {
		return partList;
	}
	public void setPartList(List<PartModel> partList) {
		this.partList = partList;
	}
	public List<PostModel> getPostList() {
		return postList;
	}
	public void setPostList(List<PostModel> postList) {
		this.postList = postList;
	}
	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}

	private UserModel user;
	
	public UserModel getUser() {
		return user;
	}
	public void setUser(UserModel user) {
		this.user = user;
	}
	
	public String toLogin() {
		return SUCCESS;
	}
	
	public String index() {
		return SUCCESS;
	}
	
	public void login() throws Exception {
		System.out.println("login action ");
		boolean check = false;
		int result = userService.loginCheck(user.getUsername(), user.getPassword());
		if (result!=0) {
			check  = true;
		}else {
			check = false;
			System.out.println("null");
		}
		if (user.getUsername() != null&&user.getPassword() != null) {
			UserModel userByName = userService.getUserByName(user.getUsername());
			if (check&&userByName.getRole()!=0) {
				ActionContext ac = ActionContext.getContext();
				ac.getSession().put("admin", user);
				this.write("success", "登录成功");
			}else {
				this.write("error", "用户名密码错误或没有权限");
			}
		} else {
			this.write("error", "用户名密码错误或没有权限");
		}
	}
	public void logout() {
		ActionContext ac = ActionContext.getContext();
		ac.getSession().remove("admin");
		this.write("success", "退出成功");
	}
	
	public String userIndex() {
		int count = userService.getUserCount();
		if (count % rows == 0) {
			pageCount = (count / rows);
		} else {
			pageCount = (count / rows) + 1;
		}
		if (page < 1) {
			page = 1;
		} else if (page > pageCount) {
			page = pageCount;
		}
		usersList = userService.getByPage(page, rows);
		return SUCCESS;
	}
	
	public String forumsIndex() throws Exception {
		int count = forumService.getForumCount();
		if (count % rows == 0) {
			pageCount = (count / rows);
		} else {
			pageCount = (count / rows) + 1;
		}
		if (page < 1) {
			page = 1;
		} else if (page > pageCount) {
			page = pageCount;
		}
		forumList = forumService.getForumByPage(page, rows);
		return SUCCESS;
	}
	
	public String partsIndex() throws Exception {
		int count = partService.getPartCount();
		if (count % rows == 0) {
			pageCount = (count / rows);
		} else {
			pageCount = (count / rows) + 1;
		}
		if (page < 1) {
			page = 1;
		} else if (page > pageCount) {
			page = pageCount;
		}
		partList = partService.getPartByPage(page, rows);
		forumList = forumService.getAllForumList();
		usersList = userService.getAllList();
		return SUCCESS;
	}
	public String postsIndex() throws Exception {
		int count = postService.getCount();
		if (count % rows == 0) {
			pageCount = (count / rows);
		} else {
			pageCount = (count / rows) + 1;
		}
		if (page < 1) {
			page = 1;
		} else if (page > pageCount) {
			page = pageCount;
		}
		postList = postService.getPostsByPage(page, rows);
		return SUCCESS;
	}
}
