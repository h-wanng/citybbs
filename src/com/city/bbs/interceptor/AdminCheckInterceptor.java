package com.city.bbs.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class AdminCheckInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		Map session = ai.getInvocationContext().getSession();
		if(session==null||session.get("admin")==null) {
			return "login";
		}else {
			return ai.invoke();
		}
	}

}
