package com.chanjet.changsha.bank.pay.spi.csbank;

import com.chanjet.changsha.bank.pay.command.AbstractValueResultApiCommand;
import com.chanjet.changsha.bank.pay.command.builder.SpiBuilder;
import com.chanjet.changsha.bank.pay.pojo.OrderCancelResponse;
import com.chanjet.changsha.bank.pay.utils.SignUtil;
import lombok.Getter;
import lombok.Setter;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: zsc
 * @create: 2020/11/9 9:28 上午
 **/
@Getter
@Setter
public class OrderCancel extends AbstractValueResultApiCommand<OrderCancelResponse> {
    /**
     * 订单ID
     */
    private String orderId;

    public OrderCancel(SpiBuilder spiBuilder) {
        super(spiBuilder);
    }


    @Override
    protected Call<OrderCancelResponse> buildCaller() {
        //加签
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("ECustId", this.eCustId);
        paramMap.put("Service_version", this.serviceVersion);
        paramMap.put("OrderId", this.orderId);
        String sign = SignUtil.sign(this.privateKeyString, paramMap);

        return this.getSpiBuilder().create(OrderCancel.Spi.class).cancel(
                this.orderId,
                this.eCustId,
                this.serviceVersion,
                sign
        );
    }

    interface Spi {

        @FormUrlEncoded
        @POST("/directBank/paygate/v0/doOrderCancel.do")
        Call<OrderCancelResponse> cancel(
                @Field("OrderId") String orderId,
                @Field("ECustId") String eCustId,
                @Field("Service_version") String serviceVersion,
                @Field("Sign") String sign
        );
    }
}
