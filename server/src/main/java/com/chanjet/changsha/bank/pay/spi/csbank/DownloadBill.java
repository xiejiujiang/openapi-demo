package com.chanjet.changsha.bank.pay.spi.csbank;

import com.chanjet.changsha.bank.pay.command.AbstractValueResultApiCommand;
import com.chanjet.changsha.bank.pay.command.builder.SpiBuilder;
import com.chanjet.changsha.bank.pay.pojo.DownloadBillResponse;
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
 * @author: zsc
 * @create: 2020/11/16 3:38 下午
 **/
@Getter
@Setter
public class DownloadBill extends AbstractValueResultApiCommand<DownloadBillResponse> {

    private String url;

    public DownloadBill(SpiBuilder spiBuilder) {
        super(spiBuilder);
    }


    @Override
    protected Call<DownloadBillResponse> buildCaller() {
        //加签
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("ECustId", this.eCustId);
        paramMap.put("Service_version", this.serviceVersion);
        String sign = SignUtil.sign(this.privateKeyString, paramMap);

        return this.getSpiBuilder().create(DownloadBill.Spi.class).download(
                this.url,
                this.eCustId,
                this.serviceVersion,
                sign
        );
    }

    interface Spi {

        @FormUrlEncoded
        @POST
        Call<DownloadBillResponse> download(
                @Url String url,
                @Field("ECustId") String eCustId,
                @Field("Service_version") String serviceVersion,
                @Field("Sign") String sign
        );
    }
}
