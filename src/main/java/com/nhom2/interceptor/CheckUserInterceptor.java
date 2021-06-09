package com.nhom2.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CheckUserInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
 		HttpSession session = request.getSession();
 		//Check user nếu chưa đăng nhập thì quay lại login
 		if(session.getAttribute("role")!="admin" && session.getAttribute("role")!="staff") {
			response.sendRedirect(request.getContextPath() + "/login");
			return false;
		}
		return true;
		
	}
}
