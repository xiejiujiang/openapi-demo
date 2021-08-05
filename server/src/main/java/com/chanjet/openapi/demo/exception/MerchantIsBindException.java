package com.chanjet.openapi.demo.exception;

/**
 * @author: zsc
 * @create: 2020/11/12 3:26 下午
 **/
public class MerchantIsBindException  extends  ErrorCodeException {


    private static final ErrorConst SELF_CODE = ErrorConst.MERCHANT_IS_BIND;
    /**
     *
     */
    private static final long serialVersionUID = -4036761993929994228L;
    public MerchantIsBindException(String hint) {

        super(SELF_CODE.getMessage(),hint);
    }
    public MerchantIsBindException() {
        super(SELF_CODE.getMessage());
    }
    @Override
    public int getErrorCode() {
        return  SELF_CODE.getCode();
    }

}
