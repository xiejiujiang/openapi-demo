package com.chanjet.openapi.demo.service;

import com.chanjet.openapi.demo.entity.User;
import com.chanjet.openapi.sdk.java.exception.ChanjetApiException;

import javax.servlet.http.HttpServletResponse;

/**
 * 认证服务
 *
 * @author: zsc
 * @create: 2020/11/5 3:02 下午
 **/
public interface AuthService {
    /**
     * 接收用户授权码
     *
     * @param code
     * @param response
     */
    User receiveCode(String code, HttpServletResponse response) throws ChanjetApiException;
}
