package com.city.bbs.action;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.city.bbs.service.IUserService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class PhotoProcessAction extends ActionSupport {
	
	private IUserService userService = null;
	@Autowired
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	private int uid;
	
	
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public InputStream getInputStream() throws Exception {
		InputStream userPhoto = userService.getUserPhoto(uid);
		return userPhoto;
	}
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
}
