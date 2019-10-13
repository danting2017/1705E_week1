package com.liudan.cms.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 后台管理中心拦截器
 * 
 * @author LENOVO
 *
 */
public class AdminInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession(false);
		if (null != session) {
			Object object = session.getAttribute("admin");
			if (null != object) {
				return true;
			}
		}
		response.sendRedirect("/passport/login");
		return false;
	}
}
