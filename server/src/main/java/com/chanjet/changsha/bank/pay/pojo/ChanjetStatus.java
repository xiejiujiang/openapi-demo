package com.chanjet.changsha.bank.pay.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: zsc
 * @create: 2020/11/10 12:57 下午
 **/
@AllArgsConstructor
@Data
public class ChanjetStatus {
    /**
     * 结果码
     */
    private String resultCode;
    /**
     * 错误码
     */
    private String errorCode;
    /**
     * 错误信息
     */
    private String errorMessage;
}
