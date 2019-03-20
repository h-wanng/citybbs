package com.city.bbs.action;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

@SuppressWarnings("serial")
public class BaseAction extends ActionSupport {
	public void write(String state, String message) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("state", state);
		jsonObject.put("message", message);
		try {
			response.getWriter().write(jsonObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void write(Map map) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		JSONObject jsonObject = JSONObject.fromObject(map);
		try {
			response.getWriter().write(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void write(Object object) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		JSONObject jsonObject = JSONObject.fromObject(object);
		try {
			response.getWriter().write(jsonObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
