package com.chanjet.changsha.bank.pay.entity;

import lombok.*;

import javax.persistence.*;
/**
 * 企业信息
 *
 * @author: zsc
 * @create: 2020/11/6 1:34 下午
 **/
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "csbank_organization")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Organization extends AbstractBaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_csbank_organization")
    @SequenceGenerator(name = "seq_csbank_organization", sequenceName = "SEQ_CSBANK_ORGANIZATION_ID", initialValue = 10000, allocationSize = 1)
    private Long id;
    /**
     * 企业ID
     */
    @Column(nullable = false, unique = true)
    private String orgId;
    /**
     * 企业永久授权码
     */
    private String permanentAuthCode;
}
