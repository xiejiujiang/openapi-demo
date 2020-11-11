package com.chanjet.changsha.bank.pay.pojo;

import com.chanjet.openapi.sdk.java.AbstractChanjetRequest;
import com.chanjet.openapi.sdk.java.enums.HttpMethod;

/**
 * @author: zsc
 * @create: 2020/11/11 2:03 下午
 **/
public class PushMerchantRequest extends AbstractChanjetRequest<PushMerchantResponse> {

    @Override
    public Class<PushMerchantResponse> getResponseClass() {
        return PushMerchantResponse.class;
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }
}
