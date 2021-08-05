package com.chanjet.openapi.demo.exception;

/**
 * @program: developer
 * @description:
 * @author: nxw
 * @create: 2020-02-13 16:04
 **/
public class ErrorParamsRuntimeException extends  ErrorCodeException {


    private static final ErrorConst SELF_CODE = ErrorConst.ERROR_PARAM;
    /**
     *
     */
    private static final long serialVersionUID = -4036761893729994228L;
    public ErrorParamsRuntimeException(String hint) {

        super(SELF_CODE.getMessage(),hint);
    }
    @Override
    public int getErrorCode() {
        return  SELF_CODE.getCode();
    }

}
