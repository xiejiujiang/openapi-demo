package com.chanjet.changsha.bank.pay.pojo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: zsc
 * @create: 2020/11/5 2:38 下午
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryOrderResponse extends CsBankCommonResponse {
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
     * 订单登记时间
     */
    @SerializedName("OrderTime")
    private String orderTime;
    /**
     * 订单支付时间
     */
    @SerializedName("PaidDate")
    private String paidDate;
    /**
     * 商户方订单号
     */
    @SerializedName("MerchOrder")
    private String merchOrder;
    /**
     * 订单金额
     */
    @SerializedName("OrderAmount")
    private String orderAmount;
    /**
     * 收银员编号
     */
    @SerializedName("StaffId")
    private String staffId;
    /**
     * 备注
     */
    @SerializedName("Remark")
    private String remark;
    /**
     * 第三方支付平台用户标识
     */
    @SerializedName("ThirdUserId")
    private String thirdUserId;
    /**
     * 优惠金额
     */
    @SerializedName("DiscountAmount")
    private String discountAmount;
    /**
     * 支付方式
     */
    @SerializedName("PayMethod")
    private String payMethod;
}
