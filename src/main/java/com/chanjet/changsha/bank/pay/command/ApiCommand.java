package com.chanjet.changsha.bank.pay.command;

import com.chanjet.changsha.bank.pay.exception.ApiFailtureException;
import com.chanjet.changsha.bank.pay.exception.BadNetworkExcepiton;
import com.chanjet.openapi.sdk.java.common.ErrorInfo;

/**
 * @author: zsc
 * @create: 2020/11/5 1:41 下午
 **/
public interface ApiCommand<T> {
    T excute() throws ApiFailtureException, BadNetworkExcepiton;

    ErrorInfo getErrorInfo();
}
