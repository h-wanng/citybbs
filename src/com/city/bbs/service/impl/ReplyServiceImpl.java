package com.city.bbs.service.impl;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.city.bbs.model.PostModel;
import com.city.bbs.model.ReplyModel;
import com.city.bbs.service.IReplyService;

@Service("replyServiceImpl")
@Transactional
public class ReplyServiceImpl implements IReplyService {

	private SessionFactory sf;

	@Autowired
	public ReplyServiceImpl(SessionFactory sf) {
		this.sf = sf;
	}

	@Autowired
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	@Override
	public void addReply(ReplyModel rm) {
		sf.getCurrentSession().save(rm);

	}

	@Override
	public void updateReply(ReplyModel rm) {
		sf.getCurrentSession().update(rm);

	}

	@Override
	public ReplyModel getReplyById(int replyId) {
		return sf.getCurrentSession().get(ReplyModel.class, replyId);
	}

	@Override
	public void deleteReply(ReplyModel rm) {
		sf.getCurrentSession().delete(rm);

	}
	//this method is stupid, get all replies? 
	@Override
	public List<ReplyModel> getAllReplyList() {
		
		return null;
	}

	@Override
	public List<ReplyModel> getReplyByPost(int postId) {
		String hql = "from ReplyModel where post.po_id = ? order by replyTime asc";
		Query<ReplyModel> query = sf.getCurrentSession().createQuery(hql, ReplyModel.class);
		query.setParameter(0, postId);
		List<ReplyModel> rList = query.getResultList();
		return rList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ReplyModel getReply(ReplyModel rm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getReplyCount(int postsId) {
		PostModel post = sf.getCurrentSession().get(PostModel.class, postsId);
		Set<ReplyModel> replies = post.getReplies();
		return replies.size();
	}

	@Override
	public List<ReplyModel> getReplyByPage(int page, int rows, int postsId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteReplyByPost(int postId) {
		PostModel post = sf.getCurrentSession().get(PostModel.class, postId);
		Set<ReplyModel> replies = post.getReplies();
		for (ReplyModel reply : replies) {
			sf.getCurrentSession().delete(reply);
		}
	}

}
