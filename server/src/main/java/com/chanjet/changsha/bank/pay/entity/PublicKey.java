package com.chanjet.changsha.bank.pay.entity;

import lombok.*;

import javax.persistence.*;

/**
 * @author: zsc
 * @create: 2020/11/6 1:42 下午
 **/
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "csbank_public_key")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublicKey extends AbstractBaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_csbank_public_key")
    @SequenceGenerator(name = "seq_csbank_public_key", sequenceName = "SEQ_CSBANK_PUBLIC_KEY_ID", initialValue = 10000, allocationSize = 1)
    private Long id;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 公钥字符串
     */
    @Column(nullable = false, length = 4096)
    private String key;
}
