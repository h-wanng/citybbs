package com.city.bbs.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class IpCheckInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		System.out.println("IP Check Interceptor");
		String ip = ServletActionContext.getRequest().getRemoteAddr();
		System.out.println(ip);
		if (ip.equals("127.0.0.0")) {
			System.out.println("Ip被禁止访问");
			return "iperror";
		} else {
			return ai.invoke();
		}
	}

}
