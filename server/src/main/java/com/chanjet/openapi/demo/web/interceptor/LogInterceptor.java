package com.chanjet.openapi.demo.web.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @program: developer
 * @description:
 * @author: nxw
 * @create: 2020-02-12 09:17
 **/
public class LogInterceptor extends HandlerInterceptorAdapter {
	private static final  String  TRACE_KEY = "TRACEID";
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		try {
			if(handler instanceof ResourceHttpRequestHandler) {
				return true;
			}
			Cookie[] cookies = request.getCookies();
			String traceId = "";
			if (null != cookies) {
				for (Cookie cookie : cookies) {
					if (StringUtils.equals(TRACE_KEY, cookie.getName())) {
						traceId = cookie.getValue();
						break;
					}
				}
			}
			if (StringUtils.isEmpty(traceId)) {
				traceId = UUID.randomUUID().toString();
				Cookie cookie = new Cookie(TRACE_KEY, traceId);
				cookie.setPath("/");
				response.addCookie(cookie);
			}

			MDC.put("sessionId", traceId);
			MDC.put("url", request.getServletPath());
		}catch (Exception e){
			return false;
		}
		return true;
	}
}
