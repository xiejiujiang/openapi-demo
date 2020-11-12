package com.chanjet.changsha.bank.pay.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: changsha-bank-pay
 * @description:
 * @author: nxw
 * @create: 2020-11-12 09:54
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookEntity {
    /**
     * 账套ID
     */
    private Long id;
    /**
     * 账套名称
     */
    private String name;

}
