package com.chanjet.changsha.bank.pay.command;

import com.chanjet.changsha.bank.pay.command.builder.SpiBuilder;
import com.chanjet.changsha.bank.pay.exception.ApiFailtureException;
import com.chanjet.changsha.bank.pay.exception.BadNetworkExcepiton;
import com.chanjet.openapi.sdk.java.common.ErrorInfo;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

/**
 * @author: zsc
 * @create: 2020/11/5 1:50 下午
 **/
@Slf4j
public abstract class AbstractRetrofitApiCommand<E, T> implements ApiCommand<T> {
    private final SpiBuilder spiBuilder;
    protected Response<E> response;
    private Call<E> caller;
    protected ErrorInfo errorInfo;
    protected T result;

    public AbstractRetrofitApiCommand(SpiBuilder spiBuilder) {
        this.spiBuilder = spiBuilder;
    }

    @Override
    public T excute() throws ApiFailtureException, BadNetworkExcepiton {
        this.doCaller();
        return this.result;
    }

    private void doCaller() throws ApiFailtureException, BadNetworkExcepiton {
        this.caller = this.buildCaller();
        this.takeResponse();
        this.extractResult();
    }

    private void takeResponse() throws BadNetworkExcepiton, ApiFailtureException {
        ErrorInfo errorInfo;
        try {
            Response<E> response = this.caller.execute();
            log.debug("response status:" + response.code());
            this.response = response;
        } catch (IOException ioException) {
            errorInfo = new ErrorInfo();
            errorInfo.setMsg("marketing api io exception:" + ioException.getMessage());
            this.errorInfo = errorInfo;
            log.error("command [" + this.getClass().getSimpleName() + "] io exception:" + ioException.getMessage());
            throw new BadNetworkExcepiton("marketing api io exception:" + ioException.getMessage(), ioException);
        } catch (Throwable throwable) {
            errorInfo = new ErrorInfo();
            errorInfo.setMsg("command [" + this.getClass().getSimpleName() + "]  exception:" + throwable.getMessage());
            log.error("command [" + this.getClass().getSimpleName() + "]  exception:" + throwable.getMessage());
            throw new ApiFailtureException("endpoint unkonw exception:" + throwable.getMessage(), throwable);
        }
    }

    @Override
    public ErrorInfo getErrorInfo() {
        return this.errorInfo;
    }

    public SpiBuilder getSpiBuilder() {
        return this.spiBuilder;
    }

    protected abstract void extractResult() throws ApiFailtureException;

    protected abstract Call<E> buildCaller();
}

