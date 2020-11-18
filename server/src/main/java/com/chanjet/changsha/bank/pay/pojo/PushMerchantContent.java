package com.chanjet.changsha.bank.pay.pojo;

import com.chanjet.changsha.bank.pay.entity.Merchant;
import com.chanjet.openapi.sdk.java.AbstractChanjetContent;
import lombok.*;

/**
 * @author zsc
 * @create 2020/11/11 2:12 下午
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PushMerchantContent extends AbstractChanjetContent {
    /**
     * 企业ID
     */
    private String orgId;
    /**
     * 账套ID
     */
    private String fieldId;
    /**
     * 商户ID
     */
    private String merchanId;
    /**
     * 商户名称
     */
    private String name;
    /**
     * 申请的类型
     */
    private String type;
    /**
     * 银行名称
     */
    private String bankName;
    /**
     * 账户号
     */
    private String accountName;
    /**
     * 开放平台appKey
     */
    private String appKey;
    /**
     * 支付服务提供者名称
     */
    private String serviceName;


    public static PushMerchantContent getInstance(Merchant merchant,String appKey){
       return PushMerchantContent.builder()
                .orgId(merchant.getOrgId())
                .fieldId(merchant.getBookId())
                .merchanId(merchant.getMerchanId())
                .name(merchant.getName())
                .type("OPEN")
                .bankName(merchant.getBankName())
                .accountName(merchant.getAccountName())
                .serviceName("长沙银行")
                .appKey(appKey)
                .build();
    }
}
