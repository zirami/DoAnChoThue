package com.nhom2.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
 		HttpSession session = request.getSession();
		if(session.getAttribute("role")=="admin" || session.getAttribute("role")=="staff") {
//			request.setAttribute("admin", true);
			response.sendRedirect(request.getContextPath() + "/phieumuon");
			return false;
		}
		return true;
		
	}

}
