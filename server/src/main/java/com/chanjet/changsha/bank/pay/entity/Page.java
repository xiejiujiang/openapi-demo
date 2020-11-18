package com.chanjet.changsha.bank.pay.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: developer
 * @description:
 * @author: nxw
 * @create: 2020-03-03 12:02
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Page<T>{
    private static final long serialVersionUID = -8583985880524976001L;
    Long totalCount;
    Integer totalPages;
    Integer currentPage;
    private List<T> resultList;

}