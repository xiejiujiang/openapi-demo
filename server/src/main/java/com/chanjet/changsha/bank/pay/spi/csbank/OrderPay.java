package com.chanjet.changsha.bank.pay.spi.csbank;

import com.chanjet.changsha.bank.pay.command.AbstractValueResultApiCommand;
import com.chanjet.changsha.bank.pay.command.builder.SpiBuilder;
import com.chanjet.changsha.bank.pay.pojo.OrderPayResponse;
import com.chanjet.changsha.bank.pay.utils.SignUtil;
import lombok.Getter;
import lombok.Setter;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 付款码支付
 *
 * @author: zsc
 * @create: 2020/11/5 2:06 下午
 **/
@Getter
@Setter
public class OrderPay extends AbstractValueResultApiCommand<OrderPayResponse> {
    /**
     * 异步通知地址
     */
    private String backUrl;
    /**
     * 微信或支付宝的支付码
     */
    private String cardNo;
    /**
     * 商户方订单号
     */
    private String merchOrder;
    /**
     * 订单金额
     */
    private String orderAmount;
    /**
     * 支付方式
     */
    private String payMethod;
    /**
     * 订单备注
     */
    private String remark;

    private String url;

    public OrderPay(SpiBuilder spiBuilder) {
        super(spiBuilder);
    }


    @Override
    protected Call<OrderPayResponse> buildCaller() {
        //加签
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("ECustId", this.eCustId);
        paramMap.put("Service_version", this.serviceVersion);
        paramMap.put("CardNo", this.cardNo);
        paramMap.put("MerchOrder", this.merchOrder);
        paramMap.put("OrderAmount", this.orderAmount);
        paramMap.put("PayMethod", this.payMethod);
        paramMap.put("Remark", this.remark);
        paramMap.put("BackUrl",this.backUrl);
        String sign = SignUtil.sign(this.privateKeyString, paramMap);

        return this.getSpiBuilder().create(OrderPay.Spi.class).pay(
                this.url,
                this.backUrl,
                this.cardNo,
                this.eCustId,
                this.merchOrder,
                this.orderAmount,
                this.payMethod,
                this.remark,
                this.serviceVersion,
                sign
        );
    }

    interface Spi {

        @FormUrlEncoded
        @POST
        Call<OrderPayResponse> pay(
                @Url String url,
                @Field("BackUrl") String backUrl,
                @Field("CardNo") String cardNo,
                @Field("ECustId") String eCustId,
                @Field("MerchOrder") String merchOrder,
                @Field("OrderAmount") String orderAmount,
                @Field("PayMethod") String payMethod,
                @Field("Remark") String remark,
                @Field("Service_version") String serviceVersion,
                @Field("Sign") String sign
        );
    }
}
