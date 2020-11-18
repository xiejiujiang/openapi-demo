package com.chanjet.changsha.bank.pay.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 下载对账单响应
 *
 * @author: zsc
 * @create: 2020/11/16 3:47 下午
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class DownloadBillResponse extends CsBankCommonResponse {
    private String data;
}
