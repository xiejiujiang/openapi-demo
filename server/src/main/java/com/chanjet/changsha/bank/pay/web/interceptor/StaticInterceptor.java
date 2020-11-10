package com.chanjet.changsha.bank.pay.web.interceptor;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: developer
 * @description:
 * @author: zhangliang
 * @create: 2020-02-12 09:17
 **/
@Log4j2
public class StaticInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof ResourceHttpRequestHandler && !request.getServletPath().contains(".")) {
			request.getRequestDispatcher("/index.html").forward(request, response);
			return true;
		}
		return super.preHandle(request, response, handler);
	}


}
