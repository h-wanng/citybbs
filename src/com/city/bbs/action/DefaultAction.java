package com.city.bbs.action;

import org.springframework.stereotype.Controller;

import com.city.bbs.exception.NotFoundException;

@SuppressWarnings("serial")
@Controller
public class DefaultAction extends BaseAction {
	
	public void defaultAction() throws NotFoundException {
		System.out.println("defaultAction in");
		throw new NotFoundException("页面找不到");
	}
}
