package com.chanjet.changsha.bank.pay.dto;

import com.chanjet.changsha.bank.pay.entity.AbstractBaseEntity;
import lombok.*;

import javax.persistence.*;

/**
 * @author: zsc
 * @create: 2020/11/6 1:42 下午
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KeyDto{

    private Long id;
    /**
     * 文件名称
     */
    private String fileName;
}
