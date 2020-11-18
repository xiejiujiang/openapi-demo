package com.chanjet.changsha.bank.pay.spi.csbank;

import com.chanjet.changsha.bank.pay.command.AbstractValueResultApiCommand;
import com.chanjet.changsha.bank.pay.command.builder.SpiBuilder;
import com.chanjet.changsha.bank.pay.pojo.QueryOrderResponse;
import com.chanjet.changsha.bank.pay.utils.SignUtil;
import lombok.Getter;
import lombok.Setter;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

import java.util.HashMap;
import java.util.Map;

/**
 * 查询支付订单
 *
 * @author: zsc
 * @create: 2020/11/5 2:07 下午
 **/
@Getter
@Setter
public class QueryOrder extends AbstractValueResultApiCommand<QueryOrderResponse> {
    /**
     * 商户方订单号
     */
    private String merchOrder;
    private String url;

    public QueryOrder(SpiBuilder spiBuilder) {
        super(spiBuilder);
    }


    @Override
    protected Call<QueryOrderResponse> buildCaller() {
        //加签
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("ECustId", this.eCustId);
        paramMap.put("Service_version", this.serviceVersion);
        paramMap.put("MerchOrder", this.merchOrder);
        String sign = SignUtil.sign(this.privateKeyString, paramMap);
        return this.getSpiBuilder().create(QueryOrder.Spi.class).queryOrder(
                this.url,
                this.merchOrder,
                this.eCustId,
                this.serviceVersion,
                sign
        );
    }

    interface Spi {

        @FormUrlEncoded
        @POST
        Call<QueryOrderResponse> queryOrder(
                @Url String url,
                @Field("MerchOrder") String merchOrder,
                @Field("ECustId") String eCustId,
                @Field("Service_version") String serviceVersion,
                @Field("Sign") String sign
        );
    }
}

