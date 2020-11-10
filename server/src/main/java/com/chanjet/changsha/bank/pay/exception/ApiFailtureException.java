package com.chanjet.changsha.bank.pay.exception;

/**
 * @author: zsc
 * @create: 2020/11/5 1:39 下午
 **/
public class ApiFailtureException extends Exception {
    private static final long serialVersionUID = -8205653822944559720L;

    public ApiFailtureException(String msg) {
        super(msg);
    }

    public ApiFailtureException(String msg, Throwable e) {
        super(msg, e);
    }
}
