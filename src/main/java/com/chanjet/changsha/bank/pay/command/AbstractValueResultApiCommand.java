package com.chanjet.changsha.bank.pay.command;

import com.chanjet.changsha.bank.pay.command.builder.SpiBuilder;
import com.chanjet.changsha.bank.pay.exception.ApiFailtureException;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

/**
 * @author: zsc
 * @create: 2020/11/5 1:48 下午
 **/
@Getter
@Setter
public abstract class AbstractValueResultApiCommand<T> extends AbstractRetrofitApiCommand<T, T> implements CsBankCommand<T> {
    /**
     * 长沙银行的商户ID
     */
    protected String eCustId;
    /**
     * 长沙银行的服务版本
     */
    protected String serviceVersion = "1.0";
    /**
     * 私钥字符串
     */
    protected String privateKeyString;

    public AbstractValueResultApiCommand(SpiBuilder spiBuilder) {
        super(spiBuilder);
    }

    @Override
    protected void extractResult() throws ApiFailtureException {
        if (!this.response.isSuccessful()) {
            String msg = "response code:" + this.response.code();

            try {
                msg = msg + " body:" + this.response.errorBody().string();
            } catch (IOException var3) {
            }

            throw new ApiFailtureException(msg);
        } else {
            T apiBody = this.response.body();
            this.result = apiBody;
        }
    }
}
