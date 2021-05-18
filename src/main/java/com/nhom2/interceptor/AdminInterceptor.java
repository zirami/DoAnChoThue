package com.nhom2.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AdminInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		if (session.getAttribute("role") == "staff") {
			response.sendRedirect(request.getContextPath() + "/phieumuon");
			return false;
		}
		if (session.getAttribute("role") != "admin") {
			response.sendRedirect(request.getContextPath() + "/login");
			return false;
		}
		return true;

	}
}
