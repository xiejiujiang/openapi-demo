package com.chanjet.changsha.bank.pay.controller;

import com.chanjet.changsha.bank.pay.annotation.ApiRestController;
import com.chanjet.changsha.bank.pay.config.AppConfig;
import com.chanjet.changsha.bank.pay.entity.User;
import com.chanjet.changsha.bank.pay.service.AuthService;
import com.chanjet.openapi.sdk.java.exception.ChanjetApiException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: zsc
 * @create: 2020/11/5 3:05 下午
 **/
@Log4j2
@ApiRestController
@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private AppConfig appConfig;

    /**
     * 接收用户授权码
     *
     * @param code
     * @param response
     * @throws IOException
     * @throws ChanjetApiException
     */
    @GetMapping("receiveCode")
    public void receiveCode(String code, HttpServletResponse response) throws IOException, ChanjetApiException {
        //接收code后返回用户基本信息
        User user = authService.receiveCode(code, response);
        //设置Cookie并重定向到前端页面
        Cookie cookie = new Cookie("token", user.getToken());
        cookie.setPath("/");
        cookie.setHttpOnly(false);
        cookie.setSecure(false);
        response.addCookie(cookie);
        response.sendRedirect(appConfig.getFrontUrl() + "?name=" + user.getName());
    }

    /**
     * 模拟前端页面获取cookie
     *
     * @param token
     * @return
     */
    @RequestMapping("test")
    public String test(@CookieValue("token") String token, @RequestParam String name) {
        log.info("token:{},name:{}", token, name);
        return token;
    }
}
