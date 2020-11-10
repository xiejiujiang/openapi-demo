package com.chanjet.changsha.bank.pay.exception;

/**
 * @author: zsc
 * @create: 2020/11/5 1:40 下午
 **/
public class BadNetworkExcepiton extends Exception {
    private static final long serialVersionUID = -7554894901307596423L;

    public BadNetworkExcepiton(String msg, Throwable e) {
        this.addSuppressed(e);
    }
}