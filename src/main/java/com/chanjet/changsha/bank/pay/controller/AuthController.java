package com.chanjet.changsha.bank.pay.controller;

import com.chanjet.changsha.bank.pay.entity.User;
import com.chanjet.changsha.bank.pay.service.AuthService;
import com.chanjet.openapi.sdk.java.exception.ChanjetApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: zsc
 * @create: 2020/11/5 3:05 下午
 **/
@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthService authService;

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
        User user = authService.receiveCode(code, response);
        //设置Cookie并重定向到前端页面
        Cookie cookie = new Cookie("token", user.getToken());
        cookie.setPath("/");
        cookie.setHttpOnly(false);
        cookie.setSecure(false);
        response.addCookie(cookie);
        response.sendRedirect("http://17f1bba78b51.ngrok.io/auth/test");
    }

    /**
     * 模拟前端页面获取cookie
     *
     * @param token
     * @return
     */
    @RequestMapping("test")
    public String test(@CookieValue("token") String token, @RequestBody String body) {
        System.out.println("长沙银行回调信息:" + body);
        return token;
    }
}
