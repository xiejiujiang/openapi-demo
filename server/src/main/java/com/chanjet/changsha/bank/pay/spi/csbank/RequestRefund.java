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
    /**
     * 员工ID
     */
    private String staffId;

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
        paramMap.put("StaffId", this.staffId);
        String sign = SignUtil.sign(this.privateKeyString, paramMap);
        return this.getSpiBuilder().create(RequestRefund.Spi.class).requestRefund(
                this.orderId,
                this.cancelReason,
                this.refundAmount,
                this.eRefundSn,
                this.staffId,
                this.eCustId,
                this.serviceVersion,
                sign
        );
    }

    interface Spi {

        @FormUrlEncoded
        @POST("/directBank/newHX105/directBank/paygate/h5/thirdpartyRefund.do")
        Call<RequestRefundResponse> requestRefund(
                @Field("OrderId") String orderId,
                @Field("CancelReason") String cancelReason,
                @Field("RefundAmount") String refundAmount,
                @Field("ERefundSn") String eRefundSn,
                @Field("StaffId") String staffId,
                @Field("ECustId") String eCustId,
                @Field("Service_version") String serviceVersion,
                @Field("Sign") String sign
        );
    }
}



