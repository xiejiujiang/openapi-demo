package com.chanjet.changsha.bank.pay.dto;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @NotBlank
    private String bookId;
    /**
     * 商户ID，长沙银行的ECustId
     */
    @NotBlank
    private String merchanId;
    /**
     * 银行名称
     */
    @NotBlank
    private String bankName;
    /**
     * 账户名
     */
    @NotBlank
    private String accountName;
    /**
     * 商户名称
     */
    @NotBlank
    private String name;
    /**
     * 私钥的id
     */
    @NotNull
    private Long privateKeyId;
    /**
     * 公钥的id
     */
    @NotNull
    private Long publicKeyId;
}
