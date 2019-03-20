package com.city.bbs.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.city.bbs.model.UserModel;
import com.city.bbs.service.IUserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Validateable;

@SuppressWarnings("serial")
@Controller
public class LoginAction extends BaseAction implements Validateable {
	private IUserService userService = null;

	@Autowired
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	private String username;
	private String password;
	private boolean remember;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRemember() {
		return remember;
	}

	public void setRemember(boolean remember) {
		this.remember = remember;
	}

	public void login() throws Exception {
		boolean check = false;
		// UserModel user = userService.getUserByName(username);
		// boolean check = userService.loginCheck(username, password);
		ActionContext ac = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) ac.get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
		int resultCount = userService.loginCheck(username, password);
		if (resultCount != 0) {
			check = true;
		} else {
			check = false;
			System.out.println("null");
		}
		if (username != null && password != null) {
			if (check) {
				if (remember == true) {
					Cookie unCookie = new Cookie("username", username);
					Cookie pwCookie = new Cookie("password", password);
					unCookie.setPath("/");
					pwCookie.setPath("/");
					unCookie.setMaxAge(30);
					pwCookie.setMaxAge(30);
					response.addCookie(unCookie);
					response.addCookie(pwCookie);
				}
				UserModel user = userService.getUserByName(username);
				ac.getSession().put("user", user);
				this.write("success", "登录成功");
				System.out.println("登录成功");
			} else {
				this.write("success", "登录失败 请检查用户名密码");
				System.out.println("登录失败 请检查用户名密码");
			}
		} else {
			this.write("success", "登录失败 请检查用户名密码");
			System.out.println("登录失败 请检查用户名密码");
		}
	}

	@Override
	public void validate() {
		// TODO Auto-generated method stub
		System.out.println("输入验证");
		if (username == null)
			addActionError("请输入用户名");
		if (username.length() < 3 || username.length() > 16)
			addActionError("请输入正确的用户名");
		if (password == null)
			addActionError("请输入密码");
		if (password.length() < 3 || password.length() > 16)
			addActionError("请输入正确的密码");
		super.validate();
	}

}
