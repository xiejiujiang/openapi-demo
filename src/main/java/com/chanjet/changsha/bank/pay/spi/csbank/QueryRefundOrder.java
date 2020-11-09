package com.chanjet.changsha.bank.pay.spi.csbank;

import com.chanjet.changsha.bank.pay.command.AbstractValueResultApiCommand;
import com.chanjet.changsha.bank.pay.command.builder.SpiBuilder;
import com.chanjet.changsha.bank.pay.pojo.QueryRefundOrderResponse;
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
 * 查询退款订单
 *
 * @author: zsc
 * @create: 2020/11/5 2:08 下午
 **/
@Getter
@Setter
public class QueryRefundOrder extends AbstractValueResultApiCommand<QueryRefundOrderResponse> {
    /**
     * 订单编号
     */
    private String orderId;

    public QueryRefundOrder(SpiBuilder spiBuilder) {
        super(spiBuilder);
    }


    @Override
    protected Call<QueryRefundOrderResponse> buildCaller() {
        //加签
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("ECustId", this.eCustId);
        paramMap.put("Service_version", this.serviceVersion);
        paramMap.put("OrderId", this.orderId);
        String sign = SignUtil.sign(this.privateKeyString, paramMap);
        return this.getSpiBuilder().create(QueryRefundOrder.Spi.class).queryRefundOrder(
                this.orderId,
                this.eCustId,
                this.serviceVersion,
                sign
        );
    }

    interface Spi {

        @FormUrlEncoded
        @POST("/directBank/paygate/h5/thirdpartyQueryRefundOrder.do")
        Call<QueryRefundOrderResponse> queryRefundOrder(
                @Field("OrderId") String orderId,
                @Field("ECustId") String eCustId,
                @Field("Service_version") String serviceVersion,
                @Field("Sign") String sign
        );
    }
}


