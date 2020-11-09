package com.chanjet.changsha.bank.pay.pojo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author: zsc
 * @create: 2020/11/5 2:35 下午
 **/
@Data
public class CsBankCommonResponse {
    @SerializedName("STATUS")
    private String status;
    @SerializedName("MSG")
    private String msg;
}
