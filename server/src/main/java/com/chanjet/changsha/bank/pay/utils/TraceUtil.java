package com.chanjet.changsha.bank.pay.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @program: developer
 * @description:
 * @author: nxw
 * @create: 2020-02-12 09:17
 **/
public class TraceUtil {

	private TraceUtil(){}
	public static String getTraceId() {
		ServletRequestAttributes reqAttr = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());

		if (reqAttr == null){
			return "";
		}
		HttpServletRequest req = reqAttr.getRequest();

		HttpSession session = req.getSession();

		return session.getId();
	}
}
