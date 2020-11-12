package com.chanjet.changsha.bank.pay.dto;

import lombok.*;

import javax.persistence.*;

/**
 * 商户信息
 *
 * @author: zsc
 * @create: 2020/11/6 1:40 下午
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MerchantSaveDto {


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
