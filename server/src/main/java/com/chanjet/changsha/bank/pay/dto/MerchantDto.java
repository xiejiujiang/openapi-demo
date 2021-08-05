package com.chanjet.changsha.bank.pay.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zsc
 * @create: 2020/11/9 2:17 下午
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
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
     * 账套名称
     */
    private String bookName;
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
     * 私钥文件名称
     */
    private String privateKeyFileName;
    /**
     * 公钥的名称
     */
    private String publicKeyFileName;
}
