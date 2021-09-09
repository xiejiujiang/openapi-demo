package com.chanjet.openapi.demo.entity;

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
     * 用户的名称
     */
    private String name;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 企业ID
     */
    private String orgId;
    /**
     * 应用名
     */
    private String appName;
    /**
     * 用户token
     */
    @Column(nullable = false, unique = true, length = 2048)
    private String token;
    /**
     * token过期时间，单位s
     */
    private Long expiresIn;
    /**
     * 用户永久授权码
     */
    private String userAuthPermanentCode;
    /**
     * 更新令牌
     */
    private String refreshToken;

    /**
     * 更新令牌过期时间，单位s
     */
    private Long refreshExpiresIn;
}
