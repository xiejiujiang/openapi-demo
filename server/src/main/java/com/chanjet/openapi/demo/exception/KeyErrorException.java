package com.chanjet.openapi.demo.exception;

/**
 * @program: changsha-bank-pay
 * @description: 公私钥错误
 * @author: nxw
 * @create: 2020-11-11 15:37
 **/
public class KeyErrorException  extends  ErrorCodeException {


    private static final ErrorConst SELF_CODE = ErrorConst.KEY_ERROR;
    /**
     *
     */
    private static final long serialVersionUID = -4036761993929994228L;
    public KeyErrorException(String hint) {

        super(SELF_CODE.getMessage(),hint);
    }
    public KeyErrorException() {
        super(SELF_CODE.getMessage());
    }
    @Override
    public int getErrorCode() {
        return  SELF_CODE.getCode();
    }

}
