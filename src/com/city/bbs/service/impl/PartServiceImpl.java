package com.city.bbs.service.impl;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.city.bbs.model.ForumModel;
import com.city.bbs.model.PartModel;
import com.city.bbs.service.IPartService;

@Service("partServiceImpl")
@Transactional
public class PartServiceImpl implements IPartService {
	
	private SessionFactory sf;

	@Autowired
	public PartServiceImpl(SessionFactory sf) {
		this.sf = sf;
	}

	@Autowired
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public void addPart(PartModel pm) throws Exception {
		sf.getCurrentSession().save(pm);

	}

	@Override
	public void updatePart(PartModel pm) throws Exception {
		sf.getCurrentSession().update(pm);
		
	}

	@Override
	public PartModel getPartById(int id) throws Exception {
		PartModel part = sf.getCurrentSession().get(PartModel.class, id);
		return part;
	}

	@Override
	public void deletePart(PartModel fm) throws Exception {
		sf.getCurrentSession().delete(fm);

	}

	@Override
	public List<PartModel> getAllPartList() throws Exception {
		String hql = "from PartModel";
		Query<PartModel> query = sf.getCurrentSession().createQuery(hql, PartModel.class);
		List<PartModel> plist = query.getResultList();
		return plist;
	}

	@Override
	public List<PartModel> getAllPartListByForum(int forumId) throws Exception {
		String hql = "from PartModel where forum.f_id = ?";
		Query<PartModel> query = sf.getCurrentSession().createQuery(hql, PartModel.class);
		query.setParameter(0, forumId);
		List<PartModel> pList = query.getResultList();
		return pList;
	}

	@Override
	public List<PartModel> getPartByPage(int page, int rows) throws Exception {
		String hql = "from PartModel";
		Query<PartModel> query = sf.getCurrentSession().createQuery(hql, PartModel.class);
		query.setFirstResult(rows*(page-1));
		query.setMaxResults(rows);
		List<PartModel> plist = query.getResultList();
		return plist;
	}

	@Override
	public int getPartCountByForum(int forumId) throws Exception {
		ForumModel forum = sf.getCurrentSession().get(ForumModel.class, forumId);
		Set<PartModel> parts = forum.getParts();
		return parts.size();
	}

	@Override
	public PartModel getPart(PartModel fm) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPartCount() throws Exception {
		Number count = (Number)sf.getCurrentSession().createQuery("select count(*) from PartModel").uniqueResult();
		return count.intValue();
	}

}
