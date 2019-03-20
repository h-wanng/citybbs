package com.city.bbs.action;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller
public class LogoutAction extends BaseAction {
	
	public void logout() {
		ActionContext ac = ActionContext.getContext();
		ac.getSession().remove("user");
		this.write("success", "退出成功");
	}
}
