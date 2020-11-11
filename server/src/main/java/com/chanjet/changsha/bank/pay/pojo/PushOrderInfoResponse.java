package com.chanjet.changsha.bank.pay.pojo;

import com.chanjet.openapi.sdk.java.AbstractChanjetResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: zsc
 * @create: 2020/11/11 1:42 下午
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class PushOrderInfoResponse extends AbstractChanjetResponse {
    private boolean status;
}
