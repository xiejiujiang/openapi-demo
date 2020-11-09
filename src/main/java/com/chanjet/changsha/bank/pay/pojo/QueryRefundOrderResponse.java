package com.chanjet.changsha.bank.pay.pojo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author: zsc
 * @create: 2020/11/5 2:47 下午
 **/
@Data
public class QueryRefundOrderResponse extends CsBankCommonResponse {
    /**
     * 订单编号
     */
    @SerializedName("OrderId")
    private String orderId;
    /**
     * 订单状态
     */
    @SerializedName("OrderStat")
    private String orderStat;
    /**
     * 商户编号
     */
    @SerializedName("ECustId")
    private String eCustId;
    /**
     * 退货流水号
     */
    @SerializedName("IcopTrac")
    private String icopTrac;
    /**
     * 退款时间
     */
    @SerializedName("RePayDate")
    private String rePayDate;
    /**
     * 员工ID
     */
    @SerializedName("StaffId")
    private String staffId;

}
