package com.chanjet.changsha.bank.pay.entity;

import lombok.*;

import javax.persistence.*;

/**
 * 商户信息
 *
 * @author: zsc
 * @create: 2020/11/6 1:40 下午
 **/
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "csbank_merchant",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"bookId","merchanId"})
})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Merchant extends AbstractBaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_csbank_merchant")
    @SequenceGenerator(name = "seq_csbank_merchant", sequenceName = "SEQ_CSBANK_MERCHANT_ID", initialValue = 10000, allocationSize = 1)
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
