package com.city.bbs.action;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Controller
public class IndexAction extends ActionSupport {

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
}
