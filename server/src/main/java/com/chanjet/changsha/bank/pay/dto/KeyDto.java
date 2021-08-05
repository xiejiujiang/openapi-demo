package com.chanjet.changsha.bank.pay.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
