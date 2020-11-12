package com.chanjet.changsha.bank.pay.service.impl;

import com.chanjet.changsha.bank.pay.config.AppConfig;
import com.chanjet.changsha.bank.pay.dao.UserDao;
import com.chanjet.changsha.bank.pay.entity.User;
import com.chanjet.changsha.bank.pay.service.AuthService;
import com.chanjet.changsha.bank.pay.spi.chanjet.ChanjetSpi;
import com.chanjet.openapi.sdk.java.exception.ChanjetApiException;
import com.chanjet.openapi.sdk.java.response.GetTokenResponse;
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
        userDao.save(user);
        return user;
    }
}
