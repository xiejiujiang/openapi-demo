package com.chanjet.openapi.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

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
     * 账套名称
     */
    @NotBlank
    private String bookName;
    /**
     * 商户ID
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
    private Long privateKeyId;
    /**
     * 公钥的id
     */
    private Long publicKeyId;
}
