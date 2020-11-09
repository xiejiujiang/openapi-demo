package com.chanjet.changsha.bank.pay.event.handler;

import com.chanjet.changsha.bank.pay.event.ChanjetMsg;
import com.chanjet.changsha.bank.pay.event.EventHandler;
import org.springframework.stereotype.Component;

/**
 * 查询支付结果事件处理器
 *
 * @author: zsc
 * @create: 2020/11/9 2:40 下午
 **/
@Component("QUERYORDER")
public class QueryOrderHandler implements EventHandler<Object> {

    @Override
    public Object execute(ChanjetMsg<Object> chanjetMsg) {
        return null;
    }
}
