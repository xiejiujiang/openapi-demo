package com.chanjet.changsha.bank.pay.spi.csbank;

import com.chanjet.changsha.bank.pay.command.AbstractValueResultApiCommand;
import com.chanjet.changsha.bank.pay.command.builder.SpiBuilder;
import com.chanjet.changsha.bank.pay.pojo.RequestRefundResponse;
import com.chanjet.changsha.bank.pay.utils.SignUtil;
import lombok.Getter;
import lombok.Setter;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 申请退款
 *
 * @author: zsc
 * @create: 2020/11/5 2:07 下午
 **/
@Getter
@Setter
public class RequestRefund extends AbstractValueResultApiCommand<RequestRefundResponse> {
    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 退款原因
     */
    private String cancelReason = "退款";
    /**
     * 退款金额
     */
    private String refundAmount;
    /**
     * 商户退款流水号
     */
    private String eRefundSn;

    public RequestRefund(SpiBuilder spiBuilder) {
        super(spiBuilder);
    }


    @Override
    protected Call<RequestRefundResponse> buildCaller() {
        //加签
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("ECustId", this.eCustId);
        paramMap.put("Service_version", this.serviceVersion);
        paramMap.put("OrderId", this.orderId);
        paramMap.put("CancelReason", this.cancelReason);
        paramMap.put("RefundAmount", this.refundAmount);
        paramMap.put("ERefundSn", this.eRefundSn);
        String sign = SignUtil.sign(this.privateKeyString, paramMap);
        return this.getSpiBuilder().create(RequestRefund.Spi.class).requestRefund(
                this.orderId,
                this.cancelReason,
                this.refundAmount,
                this.eRefundSn,
                this.eCustId,
                this.serviceVersion,
                sign
        );
    }

    interface Spi {

        @FormUrlEncoded
        @POST("/directBank/paygate/h5/thirdpartyQueryRefundOrder.do")
        Call<RequestRefundResponse> requestRefund(
                @Field("OrderId") String orderId,
                @Field("CancelReason") String cancelReason,
                @Field("RefundAmount") String refundAmount,
                @Field("ERefundSn") String eRefundSn,
                @Field("ECustId") String eCustId,
                @Field("Service_version") String serviceVersion,
                @Field("Sign") String sign
        );
    }

    public static void main(String[] args) {
        System.out.println(URLEncoder.encode("code=38388807bd9a422e8741d307506fb403&grant_type=authorization_code&app_key=t6avXpks&redirect_uri=https://passport.utax360.cn/uauthentication/chanjet/gotoapp"));
        System.out.println(URLDecoder.decode("https://sandbox-market.chanjet.com/api/sso/action?loginKey=fda256999766466d811309a3393ff499&state=%7B%22bookCode%22%3A%225tpqal2rf7%22%2C%22pageHash%22%3A%22%22%2C%22isWorkbench%22%3Atrue%7D"));
        System.out.println(URLEncoder.encode("https://sandbox-market.chanjet.com/api/sso/action?loginKey=fda256999766466d811309a3393ff499&state={\"bookCode\":\"5tpqal2rf7\"}"));
    }
}



