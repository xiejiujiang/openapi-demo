package com.chanjet.openapi.demo.entity;

import lombok.*;

import javax.persistence.*;

/**
 * @author: zsc
 * @create: 2020/11/6 1:42 下午
 **/
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "csbank_private_key")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrivateKey extends AbstractBaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_csbank_private_key")
    @SequenceGenerator(name = "seq_csbank_private_key", sequenceName = "SEQ_CSBANK_PRIVATE_KEY_ID", initialValue = 10000, allocationSize = 1)
    private Long id;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 商户秘钥
     */
    private String key;
    /**
     * 私钥字符串
     */
    @Column(nullable = false, length = 4096)
    private String privateKeyString;
}
