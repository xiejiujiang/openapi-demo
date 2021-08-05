package com.chanjet.openapi.demo.exception;

/**
 * @author: zsc
 * @create: 2020/11/11 10:18 上午
 **/
public class NoTokenException extends  ErrorCodeException {


    private static final ErrorConst SELF_CODE = ErrorConst.NO_TOKEN;
    /**
     *
     */
    private static final long serialVersionUID = -4036761893929994228L;
    public NoTokenException(String hint) {

        super(SELF_CODE.getMessage(),hint);
    }
    public NoTokenException() {
        super(SELF_CODE.getMessage());
    }
    @Override
    public int getErrorCode() {
        return  SELF_CODE.getCode();
    }

}
