package com.chanjet.changsha.bank.pay.pojo;

import com.chanjet.openapi.sdk.java.AbstractChanjetRequest;
import com.chanjet.openapi.sdk.java.enums.HttpMethod;

/**
 * @author: zsc
 * @create: 2020/11/11 1:30 下午
 **/
public class PushOrderInfoRequest extends AbstractChanjetRequest<PushOrderInfoResponse> {


    @Override
    public Class<PushOrderInfoResponse> getResponseClass() {
        return PushOrderInfoResponse.class;
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }
}
