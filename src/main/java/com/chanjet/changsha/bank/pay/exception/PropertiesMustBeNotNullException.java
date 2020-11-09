package com.chanjet.changsha.bank.pay.exception;

/**
 * @author: zsc
 * @create: 2020/11/5 1:40 下午
 **/
public class PropertiesMustBeNotNullException extends RuntimeException {
    private static final long serialVersionUID = -3880533191588204478L;

    public PropertiesMustBeNotNullException(String msg) {
        super(msg);
    }
}
