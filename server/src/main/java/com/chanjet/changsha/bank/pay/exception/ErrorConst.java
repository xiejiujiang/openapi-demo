package com.chanjet.changsha.bank.pay.exception;

/**
 * @program: developer
 * @description:
 * @author: nxw
 * @create: 2020-02-12 09:17
 **/
public enum ErrorConst {
    INTENAL_ERROR(500, "系统内部异常");

    private int code;
    private String message;


    ErrorConst(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

}
