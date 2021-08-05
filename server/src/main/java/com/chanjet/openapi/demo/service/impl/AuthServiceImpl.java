package com.chanjet.openapi.demo.service.impl;

import com.chanjet.openapi.demo.config.AppConfig;
import com.chanjet.openapi.demo.dao.UserDao;
import com.chanjet.openapi.demo.entity.User;
import com.chanjet.openapi.demo.service.AuthService;
import com.chanjet.openapi.demo.spi.chanjet.ChanjetSpi;
import com.chanjet.openapi.sdk.java.exception.ChanjetApiException;
import com.chanjet.openapi.sdk.java.response.GetTokenResponse;
import com.chanjet.openapi.sdk.java.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: zsc
 * @create: 2020/11/5 4:56 下午
 **/
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private ChanjetSpi chanjetSpi;
    @Autowired
    private UserDao userDao;
    @Autowired
    private AppConfig appConfig;

    @Override
    public User receiveCode(String code, HttpServletResponse response) throws ChanjetApiException {
        GetTokenResponse getTokenResponse = chanjetSpi.getToken(code, appConfig.getRedirectUri());
        //保存用户信息
        User user = userDao.findUserByOrgIdAndUserId(getTokenResponse.getResult().getOrgId(), getTokenResponse.getResult().getUserId());
        if (null == user) {
            user = new User();
            user.setOrgId(getTokenResponse.getResult().getOrgId());
            user.setUserId(getTokenResponse.getResult().getUserId());
        }
        user.setToken(getTokenResponse.getResult().getAccessToken());
        user.setUserAuthPermanentCode(getTokenResponse.getResult().getUserAuthPermanentCode());
        //获取畅捷通用户信息
        UserResponse userResponse = chanjetSpi.user(user.getToken());
        user.setName(userResponse.getName());
        userDao.save(user);
        return user;
    }
}
