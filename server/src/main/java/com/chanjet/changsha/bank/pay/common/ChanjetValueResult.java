package com.chanjet.changsha.bank.pay.common;

import com.chanjet.openapi.sdk.java.common.ErrorInfo;

import java.io.Serializable;

/**
 * @author: zsc
 * @create: 2020/11/9 10:09 上午
 **/
public class ChanjetValueResult<T> implements Serializable {
    private static final long serialVersionUID = -1653051681414980905L;
    private boolean result;
    private ErrorInfo error;
    private T value;

    public ChanjetValueResult() {
    }

    public boolean isResult() {
        return this.result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public ErrorInfo getError() {
        return this.error;
    }

    public void setError(ErrorInfo error) {
        this.error = error;
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public static <T> ChanjetValueResult<T> success(T value) {
        ChanjetValueResult<T> obj = new ChanjetValueResult();
        obj.setValue(value);
        obj.setResult(true);
        return obj;
    }

    public static <T> ChanjetValueResult<T> fail(ErrorInfo error) {
        ChanjetValueResult<T> obj = new ChanjetValueResult();
        obj.setError(error);
        obj.setResult(false);
        return obj;
    }
}
