package com.chanjet.changsha.bank.pay.pojo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: zsc
 * @create: 2020/11/5 2:55 下午
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class RequestRefundResponse extends CsBankCommonResponse {
    /**
     * 商户退款流水号
     */
    @SerializedName("ERefundSn")
    private String eRefundSn;
    /**
     * 交易流水号
     */
    @SerializedName("PaymentSn")
    private String paymentSn;
}
