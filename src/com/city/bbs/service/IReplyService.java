package com.city.bbs.service;

import java.util.List;

import com.city.bbs.model.ReplyModel;


public interface IReplyService {
	public void addReply(ReplyModel rm);

	public void updateReply(ReplyModel rm);

	public ReplyModel getReplyById(int replyId);

	public void deleteReply(ReplyModel rm);

	public List<ReplyModel> getAllReplyList();

	public List<ReplyModel> getReplyByPost(int postId);

	public int getCount();

	public ReplyModel getReply(ReplyModel rm);

	public int getReplyCount(int postsId);

	public List<ReplyModel> getReplyByPage(int page, int rows, int postsId);

	public void deleteReplyByPost(int postId);
}
