package com.city.bbs.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.city.bbs.model.ForumModel;
import com.city.bbs.model.PartModel;
import com.city.bbs.service.IForumService;

@Service("forumServiceImpl")
@Transactional
public class ForumServiceImpl implements IForumService {
	
	private SessionFactory sf;

	@Autowired
	public ForumServiceImpl(SessionFactory sf) {
		this.sf = sf;
	}

	@Autowired
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public void addForum(ForumModel fm) throws Exception {
		sf.getCurrentSession().save(fm);

	}

	@Override
	public void updateForum(ForumModel fm) throws Exception {
		sf.getCurrentSession().update(fm);

	}

	@Override
	public ForumModel getForumById(int id) throws Exception {
		return sf.getCurrentSession().get(ForumModel.class, id);
		
	}

	@Override
	public void deleteForum(ForumModel fm) throws Exception {
		sf.getCurrentSession().delete(fm);

	}

	@Override
	public List<ForumModel> getAllForumList() throws Exception {
		String hql = "from ForumModel";
		Query<ForumModel> query = sf.getCurrentSession().createQuery(hql, ForumModel.class);
		List<ForumModel> list = query.getResultList();
		return list;
		
	}

	@Override
	public List<ForumModel> getForumByPage(int page, int rows) throws Exception {
		String hql = "from ForumModel";
		Query<ForumModel> query = sf.getCurrentSession().createQuery(hql, ForumModel.class);
		query.setFirstResult(rows*(page-1));
		query.setMaxResults(rows);
		List<ForumModel> list = query.getResultList();
		return list;
	}

	@Override
	public int getForumCount() throws Exception {
		Number count = (Number)sf.getCurrentSession().createQuery("select count(*) from ForumModel").uniqueResult();
		return count.intValue();
	}

	@Override
	public ForumModel getForum(ForumModel fm) throws Exception {
		return null;
	}

	@Override
	public List<PartModel> getPartsByForum(int forumId) throws Exception {
		String hql = "from PartModel where forum.f_id = ?";
		Query<PartModel> query = sf.getCurrentSession().createQuery(hql, PartModel.class);
		query.setParameter(0, forumId);
		List<PartModel> partList = query.getResultList();
		return partList;
	}

}
