package com.city.bbs.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.city.bbs.model.UserModel;
import com.city.bbs.service.IUserService;


@SuppressWarnings("serial")
@Controller
public class UserProcessAction extends BaseAction {
	private IUserService userService = null;
	@Autowired
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	private SessionFactory sf;
	@Autowired
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	private UserModel user;
	private File photo;
	private int uid;

	public int getUid() {
		return uid;
	}


	public void setUid(int uid) {
		this.uid = uid;
	}


	public UserModel getUser() {
		return user;
	}


	public void setUser(UserModel user) {
		this.user = user;
	}


	public File getPhoto() {
		return photo;
	}


	public void setPhoto(File photo) {
		this.photo = photo;
	}


	public void register() throws Exception {
		Session session = sf.openSession();
		System.out.println("register action in");
		UserModel userInDB = userService.getUserByName(user.getUsername());
		if (userInDB == null) {
			String path = ServletActionContext.getServletContext().getRealPath(".");
			File file = new File(path+"/img/city.jpg");
			InputStream inputStream = new FileInputStream(file);
			Blob blob = session.getLobHelper().createBlob(inputStream, inputStream.available());
			user.setPhoto(blob);
			user.setRegTime(new Date());
			user.setRole(0);
			userService.addUser(user);
			session.close();
			this.write("success", "注册成功");
		} else {
			this.write("error", "该用户名已经被注册");
		}
	}
	
	public String userInfo() {
		try {
			user = userService.getUserById(uid);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return SUCCESS;
	}
	
	public String changePhoto() throws IOException {
		System.out.println("changePhoto action in");
		try {
			InputStream is = new FileInputStream(photo);
			Session session = sf.openSession();
			Blob blob = session.getLobHelper().createBlob(is, is.available());
			UserModel user = userService.getUserById(uid);
			user.setPhoto(blob);
			userService.updateUsers(user);
			return SUCCESS;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
}
