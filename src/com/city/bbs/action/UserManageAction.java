package com.city.bbs.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.city.bbs.model.UserModel;
import com.city.bbs.service.IUserService;

@SuppressWarnings("serial")
@Controller
public class UserManageAction extends BaseAction {
	private IUserService userService = null;
	
	@Autowired
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	private UserModel user = null;
	
	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public void userDelete() {
		userService.deleteUser(user);
		this.write("success","删除成功");
	}
	public void userUpdate() {
		try {
			userService.updateUsers(user);
			this.write("success","修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			this.write("error","修改失败");
		}
	}
}
