package com.chanjet.changsha.bank.pay.dto;

/**
 * @author: zsc
 * @create: 2020/11/9 2:17 下午
 **/
public class MerchantDto {
    private Long id;
    /**
     * 企业ID
     */
    private String orgId;
    /**
     * 账套ID
     */
    private String bookId;
    /**
     * 商户ID，长沙银行的ECustId
     */
    private String merchanId;
    /**
     * 银行名称
     */
    private String bankName;
    /**
     * 账户名
     */
    private String accountName;
    /**
     * 商户名称
     */
    private String name;
    /**
     * 私钥的id
     */
    private Long privateKeyId;
    /**
     * 公钥的id
     */
    private Long publicKeyId;
}
