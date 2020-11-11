package com.chanjet.changsha.bank.pay.entity;

import lombok.*;

import javax.persistence.*;

/**
 * 用户信息
 *
 * @author: zsc
 * @create: 2020/11/6 11:07 上午
 **/
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "csbank_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends AbstractBaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_csbank_user")
    @SequenceGenerator(name = "seq_csbank_user", sequenceName = "SEQ_CSBANK_USER_ID", initialValue = 10000, allocationSize = 1)
    private Long id;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 企业ID
     */
    private String orgId;
    /**
     * 用户token
     */
    @Column(nullable = false, unique = true, length = 2048)
    private String token;
    /**
     * 用户永久授权码
     */
    private String userAuthPermanentCode;
}
