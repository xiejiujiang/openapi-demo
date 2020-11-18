package com.chanjet.changsha.bank.pay.web.interceptor;

import com.chanjet.changsha.bank.pay.annotation.NeedToken;
import com.chanjet.changsha.bank.pay.dao.UserDao;
import com.chanjet.changsha.bank.pay.entity.User;
import com.chanjet.changsha.bank.pay.exception.NoTokenException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @program: developer
 * @description:
 * @author: nxw
 * @create: 2020-02-19 13:16
 **/
@Component
@Slf4j
@Aspect
@Data
public class LoginAspect {

    @Autowired
    private UserDao userDao;

    private final HttpSession session;

    public LoginAspect(HttpSession session) {
        this.session = session;
    }

    @Around(value = "@annotation(com.chanjet.changsha.bank.pay.annotation.NeedToken)&&@annotation(needToken)")
    public Object around(ProceedingJoinPoint joinPoint, NeedToken needToken) throws Throwable {
        if (needToken.needToken()) {
            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            assert sra != null;
            HttpServletRequest request = sra.getRequest();
            Cookie[] cookies = request.getCookies();
            if (StringUtils.isEmpty(cookies)) {
                throw new NoTokenException();
            }
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    User user = userDao.findUserByToken(cookie.getValue());
                    if (user == null) {
                        throw new NoTokenException();
                    }
                }
            }
            return joinPoint.proceed();
        } else {
            return joinPoint.proceed();
        }

    }

}
