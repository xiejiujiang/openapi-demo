package com.chanjet.changsha.bank.pay.pojo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 付款码支付响应
 *
 * @author: zsc
 * @create: 2020/11/5 2:13 下午
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderPayResponse extends CsBankCommonResponse {
    /**
     * 二维码码串
     */
    @SerializedName("QrCode")
    private String qrCode;
    /**
     * 订单编号
     */
    @SerializedName("OrderId")
    private String orderId;
    /**
     * 第三方用户标识
     */
    @SerializedName("ThirdUserId")
    private String thirdUserId;
    /**
     * 订单时间
     */
    @SerializedName("OrderTime")
    private String orderTime;
    /**
     * 订单状态
     */
    @SerializedName("OrderStat")
    private String orderStat;
}
