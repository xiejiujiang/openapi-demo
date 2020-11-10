package com.chanjet.changsha.bank.pay.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BizResponseBean<T> {

    /**
     * 业务 处理结果 支付时候和payStatus 相同，退款时候和refundStatus相同，提出来是为了使用方好处理
     */
    public String result_code;
    /**
     * 错误码
     */
    public String error_code;
    /**
     * 错误信息
     */
    public String error_message;
    /**
     * 业务响应数据
     */
    public T data;
}