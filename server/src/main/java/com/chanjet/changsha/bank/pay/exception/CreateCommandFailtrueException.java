package com.chanjet.changsha.bank.pay.exception;

/**
 * @author: zsc
 * @create: 2020/11/5 1:40 下午
 **/
public class CreateCommandFailtrueException extends RuntimeException {
    private static final long serialVersionUID = -3880533191588204478L;

    public CreateCommandFailtrueException(String msg) {
        super(msg);
    }

    public CreateCommandFailtrueException(String msg, Throwable e) {
        super(msg, e);
    }
}
