package com.chanjet.openapi.demo.pojo;

import com.chanjet.openapi.sdk.java.AbstractChanjetResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 推送商户信息响应
 *
 * @author: zsc
 * @create: 2020/11/11 2:03 下午
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class PushMerchantResponse extends AbstractChanjetResponse {
    private boolean status;
}
