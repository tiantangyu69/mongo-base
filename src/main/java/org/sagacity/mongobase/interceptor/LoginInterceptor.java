package org.sagacity.mongobase.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.Spring;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * @author 袁鹏
 * @see Spring 拦截器的简单实现测试
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	/**
	 * 在业务处理器处理之前被调用，主要用于权限控制
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object object) throws Exception {
		String url = request.getRequestURI();// 访问的url
		System.out.println(url);
		return true;
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}
}