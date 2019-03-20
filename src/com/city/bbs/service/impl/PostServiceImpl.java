package com.city.bbs.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.city.bbs.model.PartModel;
import com.city.bbs.model.PostModel;
import com.city.bbs.service.IPostService;

@Service("postServiceImpl")
@Transactional
public class PostServiceImpl implements IPostService {
	
	private SessionFactory sf;

	@Autowired
	public PostServiceImpl(SessionFactory sf) {
		this.sf = sf;
	}

	@Autowired
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public void addPost(PostModel pom) {
		sf.getCurrentSession().save(pom);

	}

	@Override
	public void updatePost(PostModel pom) {
		sf.getCurrentSession().update(pom);

	}

	@Override
	public PostModel getPostById(int id) {
		return sf.getCurrentSession().get(PostModel.class, id);
	}

	@Override
	public void deletePost(PostModel pom) {
		sf.getCurrentSession().delete(pom);

	}

	@Override
	public List<PostModel> getAllPostList() {
		String hql = "from PostModel";
		Query<PostModel> query = sf.getCurrentSession().createQuery(hql, PostModel.class);
		List<PostModel> polist = query.getResultList();
		return polist;
	}

	@Override
	public List<PostModel> getPostsByPart(int partId) {
		String hql = "from PostModel where part.p_id = ? order by createTime asc";
		Query<PostModel> query = sf.getCurrentSession().createQuery(hql, PostModel.class);
		query.setParameter(0, partId);
		List<PostModel> postList = query.getResultList();
		return postList;
	}

	@Override
	public List<PostModel> getPostsByPage(int page, int rows) {
		String hql = "from PostModel";
		Query<PostModel> query = sf.getCurrentSession().createQuery(hql, PostModel.class);
		query.setFirstResult(rows*(page-1));
		query.setMaxResults(rows);
		List<PostModel> polist = query.getResultList();
		return polist;
	}

	@Override
	public int getCount() {
		//TODO ---that seems like result is the count of all posts---
		Number count = (Number)sf.getCurrentSession().createQuery("select count(*) from PostModel").uniqueResult();
		return count.intValue();
	}

	@Override
	public PostModel getPost(PostModel pom) {
//		Predicate preP = null;
//		CriteriaBuilder builder = sf.getCurrentSession().getCriteriaBuilder();
//		CriteriaQuery<PostModel> criteria = builder.createQuery(PostModel.class);
//		Root<PostModel> root = criteria.from(PostModel.class);
//		Predicate p1 = builder.equal(root.get("title").as(String.class), pom.getTitle().toString());
//		Predicate p2 = builder.equal(root.get("createTime").as(Date.class), pom.getCreateTime());
//		preP = builder.and(p1, p2);
//		criteria.where(preP);
//		Query<PostModel> createQuery = sf.getCurrentSession().createQuery(criteria);
//		List<PostModel> resultList = createQuery.getResultList();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createTime = formatter.format(pom.getCreateTime());
		String hql = "from PostModel where title = '"+pom.getTitle()+"' and createTime = '"+createTime+"'";
		Query<PostModel> query = sf.getCurrentSession().createQuery(hql, PostModel.class);
//		query.setParameter(0, pom.getTitle());
//		query.setParameter(1, pom.getCreateTime());
		List<PostModel> resultList = query.getResultList();
		System.out.println("resultList size "+resultList.size());
		if (resultList.size()==0) {
			return null;
		}
		return resultList.get(0);
	}
	
	@Override
	public int getCount(int partId) {
		PartModel part = sf.getCurrentSession().get(PartModel.class, partId);
		Set<PostModel> posts = part.getPosts();
		return posts.size();
	}

	@Override
	public List<PostModel> getByPage(int page, int rows, int partId) {
		String hql = "from PostModel where part.p_id = ? order by createTime desc";
		Query<PostModel> query = sf.getCurrentSession().createQuery(hql, PostModel.class);
		query.setParameter(0, partId);
		query.setFirstResult((page - 1) * rows);
		query.setMaxResults(rows);
		List<PostModel> postList = query.getResultList();
		return postList;
	}
	//get click number
	@Override
	public void clickPost(int id) {
		PostModel post = sf.getCurrentSession().get(PostModel.class, id);
		int num = post.getClickNum()+1;
		post.setClickNum(num);
		sf.getCurrentSession().saveOrUpdate(post);
	}

	@Override
	public void deletePostsByPart(int partId) {
		PartModel part = sf.getCurrentSession().get(PartModel.class, partId);
		Set<PostModel> posts = part.getPosts();
		for (PostModel post : posts) {
			sf.getCurrentSession().delete(post);
		}

	}

}
