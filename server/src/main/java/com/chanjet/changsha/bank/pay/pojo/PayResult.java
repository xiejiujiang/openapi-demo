package com.chanjet.changsha.bank.pay.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author zsc
 * @create 2020/11/11 2:59 下午
 **/
@Data
public class PayResult {
    /**
     * 签名
     */
    @JsonProperty("SignData")
    private String signData;
    /**
     * 响应码
     */
    @JsonProperty("respCode")
    private String respCode;
    /**
     * 响应信息
     */
    @JsonProperty("respMsg")
    private String respMsg;
    /**
     * 商户订单号
     */
    @JsonProperty("MerchOrder")
    private String merchOrder;
    /**
     * 订单号
     */
    @JsonProperty("OrderId")
    private String orderId;
    /**
     * 订单金额
     */
    @JsonProperty("OrderAmount")
    private String orderAmount;
    /**
     * 优惠金额
     */
    @JsonProperty("DiscountAmount")
    private String discountAmount;
    /**
     * 订单登记时间
     */
    @JsonProperty("OrderTime")
    private String orderTime;
    /**
     * 订单支付时间
     */
    @JsonProperty("PaidDate")
    private String paidDate;
    /**
     * 清算日期
     */
    @JsonProperty("SettleDate")
    private String settleDate;
    /**
     * 商户客户号
     */
    @JsonProperty("ECustId")
    private String eCustId;
    /**
     * 收银员编号
     */
    @JsonProperty("StaffId")
    private String staffId;
    /**
     * 备注
     */
    @JsonProperty("Remark")
    private String remark;
    /**
     * 第三方支付平台用户标识
     */
    @JsonProperty("ThirdUserId")
    private String thirdUserId;
    /**
     * 支付方式
     */
    @JsonProperty("PayMethod")
    private String payMethod;
    /**
     * 商户扩展
     */
    @JsonProperty("MerchExtend")
    private String merchExtend;
    /**
     * 支付通道
     */
    @JsonProperty("OutputChan")
    private String outputChan;
}
