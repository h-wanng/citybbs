package com.city.bbs.action;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.city.bbs.model.PartModel;
import com.city.bbs.model.PostModel;
import com.city.bbs.model.ReplyModel;
import com.city.bbs.model.UserModel;
import com.city.bbs.service.IPartService;
import com.city.bbs.service.IPostService;
import com.city.bbs.service.IReplyService;
import com.city.bbs.service.IUserService;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller
public class PostProcessAction extends BaseAction {
	private IPostService postService = null;
	private IPartService partService = null;
	private IReplyService replyService = null;
	private IUserService userService = null;
	@Autowired
	public void setPostService(IPostService postService) {
		this.postService = postService;
	}
	@Autowired
	public void setPartService(IPartService partService) {
		this.partService = partService;
	}
	@Autowired
	public void setReplyService(IReplyService replyService) {
		this.replyService = replyService;
	}
	@Autowired
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	private int partId;
	private int postId;
	private List<PostModel> postList;
	private List<PartModel> partList;
	private PostModel post;
	private int poCount;
	private PartModel part;
	private List<ReplyModel> replyList;
	private int reCount;
	private ReplyModel reply;
	private int page = 1;
	private int rows = 8;
	private int pageCount;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public PostModel getPost() {
		return post;
	}
	public void setPost(PostModel post) {
		this.post = post;
	}
	public PartModel getPart() {
		return part;
	}
	public void setPart(PartModel part) {
		this.part = part;
	}
	public int getPoCount() {
		return poCount;
	}
	public void setPoCount(int poCount) {
		this.poCount = poCount;
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
	public List<ReplyModel> getReplyList() {
		return replyList;
	}
	public void setReplyList(List<ReplyModel> replyList) {
		this.replyList = replyList;
	}
	public ReplyModel getReply() {
		return reply;
	}
	public void setReply(ReplyModel reply) {
		this.reply = reply;
	}
	public int getPartId() {
		return partId;
	}


	public void setPartId(int partId) {
		this.partId = partId;
	}


	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public int getReCount() {
		return reCount;
	}
	public void setReCount(int reCount) {
		this.reCount = reCount;
	}
	
	public String postIndex() throws Exception {
		List<PostModel> allPost = postService.getPostsByPart(partId);
		poCount = allPost.size();
		System.out.println("po count "+poCount);
		if (poCount % rows == 0) {
			pageCount = (poCount / rows);
		} else {
			pageCount = (poCount / rows) + 1;
		}
		if (page < 1) {
			this.page = 1;
		} else if (page > pageCount) {
			page = pageCount;
		}
		part = partService.getPartById(partId);
		UserModel manager = userService.getUserById(part.getManager());
		postList = postService.getByPage(page, rows, partId);
		partList = partService.getAllPartListByForum(part.getForum().getF_id());
		ActionContext ac = ActionContext.getContext();
		Map<String, Object> session = ac.getSession();
		session.put("partId", partId);
		session.put("manager", manager);
		return SUCCESS;
	}
	
	public String checkPost() throws Exception {
		post = postService.getPostById(postId);
		reCount = replyService.getReplyCount(postId);
		part = post.getPart();
		replyList = replyService.getReplyByPost(postId);
		postService.clickPost(postId);
		return SUCCESS;
	}
	
	public String addPost() throws Exception {
		PartModel part = partService.getPartById(partId);
		List<PostModel> allPost = postService.getPostsByPart(partId);
		partList = partService.getAllPartListByForum(part.getForum().getF_id());
		poCount = allPost.size();
		return SUCCESS;
	}
	
	public void doAddPost() throws Exception {
		System.out.println("doAddPost action in");
		ActionContext ac = ActionContext.getContext();
		if (ac.getSession().get("user") == null) {
			this.write("error", "");
		} else {
			UserModel user = (UserModel)ac.getSession().get("user");
			int pid = (int)ac.getSession().get("partId");
			Calendar cal = Calendar.getInstance(); 
			//MySQL 对于大于500毫秒的时间进位处理
			//取消new Date();用法，将Calendar.MILLISECOND统一设置为0
			cal.set(Calendar.MILLISECOND, 0);
			Date date = cal.getTime();
			PartModel p = partService.getPartById(pid);
			post.setPart(p);
			post.setUser(user);
			post.setCreateTime(date);
			post.setClickNum(0);
			postService.addPost(post);
			post = postService.getPost(post);
			Map<String, String> map = new HashMap<String, String>();
			map.put("state", "success");
			map.put("poid", String.valueOf(post.getPo_id()));
			this.write(map);
		}
	}
	
	public void addReply() {
		System.out.println("add reply action in");
		ActionContext ac = ActionContext.getContext();
		if (ac.getSession().get("user") == null) {
			this.write("error", "");
		} else {
			try {
				UserModel user = (UserModel) ac.getSession().get("user");
				PostModel pForR = postService.getPostById(postId);
				reply.setPost(pForR);
				reply.setUser(user);
				reply.setReplyTime(new Date());
				replyService.addReply(reply);
				this.write("success", "");
			} catch (Exception e) {
				this.write("error", "");
				e.printStackTrace();
			}
		}
	}
}
