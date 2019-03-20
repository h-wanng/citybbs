package com.city.bbs.service.impl;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.city.bbs.model.UserModel;
import com.city.bbs.service.IUserService;


@Service("userServiceImpl")
@Transactional
public class UserServiceImpl implements IUserService {
	
	private SessionFactory sf;

//	@Autowired
//	public UserServiceImpl(SessionFactory sf) {
//		this.sf = sf;
//	}

	@Autowired
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public void addUser(UserModel um) throws Exception {
		sf.getCurrentSession().save(um);
	}

	@Override
	public void updateUsers(UserModel um) throws Exception {
		sf.getCurrentSession().update(um);
	}

	@Override
	public UserModel getUserById(int id) throws Exception {
		return sf.getCurrentSession().get(UserModel.class, id);
	}

	@Override
	public void deleteUser(UserModel um) {
		sf.getCurrentSession().delete(um);
	}

	@Override
	public List<UserModel> getAllList() {
		String hql = "from UserModel";
		Query<UserModel> query = sf.getCurrentSession().createQuery(hql, UserModel.class);
		List<UserModel> list = query.getResultList();
		return list;
	}

	@Override
	public List<UserModel> getByPage(int page, int rows) {
		String hql = "from UserModel";
		Query<UserModel> query = sf.getCurrentSession().createQuery(hql, UserModel.class);
		query.setFirstResult(rows*(page-1));
		query.setMaxResults(rows);
		List<UserModel> userList = query.getResultList();
		return userList;
	}

	@Override
	public int getUserCount() {
		Number count = (Number)sf.getCurrentSession().createQuery("select count(*) from UserModel").uniqueResult();
		return count.intValue();
	}

	@Override
	/**can't make out what's wrong with this shit query!!! This shit get stuck in createQuery and never return a result. 
	 *It takes me a whole afternoon.stupid shit
	 *after I learn and use CriteriaBuilder...find a way to kick the shit
	**/
	public int login(String name, String password) {
//		String hql = "from UserModel as model where model.username = ?";
		String hql = "from UserModel as model where  model.username = ? and password = ?";
		Query<UserModel> query = sf.getCurrentSession().createQuery(hql, UserModel.class);
		query.setParameter(0, name);
		query.setParameter(1, password);
//		List<UserModel> userList = query.getResultList();
		int resultSize = query.getResultList().size();
//		System.out.println("userList size "+userList.size());
//		if (userList.size()==0) {
//			result = false;
//		}else {
//			for (UserModel user : userList) {
//				if (user.getUsername().equals(name)&&user.getPassword().equals(password)) {
//					result = true;
//				}else {
//					result = false;
//				}
//			}
//		}
		
		return resultSize;
	}

	@Override
	public UserModel getUserByName(String username) throws Exception {
		String hql = "from UserModel where username = '" + username +"'";
		Query<UserModel> query = sf.getCurrentSession().createQuery(hql, UserModel.class);
		List<UserModel> userList = query.getResultList();
		if (userList.size()==0) {
			return null;
		}
		return userList.get(0);
	}

	@Override
	public int loginCheck(String name, String password) throws Exception {
		Predicate preP = null;
		CriteriaBuilder builder = sf.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<UserModel> criteria = builder.createQuery(UserModel.class);
		Root<UserModel> root = criteria.from(UserModel.class);
		Predicate p1 = builder.equal(root.get("username").as(String.class), name.toString());
		Predicate p2 = builder.equal(root.get("password").as(String.class), password.toString());
		preP = builder.and(p1, p2);
		criteria.where(preP);
		Query<UserModel> createQuery = sf.getCurrentSession().createQuery(criteria);
		List<UserModel> resultList = createQuery.getResultList();
		return resultList.size();
	}

	@Override
	public InputStream getUserPhoto(int uid) throws Exception {
		String hql = "select u.photo from UserModel u where u.u_id = :uid";
		Query<Blob> query = sf.getCurrentSession().createQuery(hql, Blob.class);
		query.setParameter("uid", uid);
		Blob photo = (Blob)query.uniqueResult();
		return photo.getBinaryStream();
	}

}
