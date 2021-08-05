package com.chanjet.openapi.demo.exception;

/**
 * @program: developer
 * @description:
 * @author: nxw
 * @create: 2020-02-12 09:17
 **/
public enum ErrorConst {
    ERROR_PARAM(2002, "参数错误"),
    INTENAL_ERROR(500, "系统内部异常"),
    NO_TOKEN(401, "用户非法"),
    KEY_ERROR(2003, "秘钥错误"),
    ;

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
