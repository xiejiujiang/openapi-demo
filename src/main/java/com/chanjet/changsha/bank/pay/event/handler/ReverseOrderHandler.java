package com.chanjet.changsha.bank.pay.event.handler;

import com.chanjet.changsha.bank.pay.event.ChanjetMsg;
import com.chanjet.changsha.bank.pay.event.EventHandler;
import org.springframework.stereotype.Component;

/**
 * 撤销订单事件处理器
 *
 * @author: zsc
 * @create: 2020/11/9 2:41 下午
 **/
@Component("REVERSEORDER")
public class ReverseOrderHandler implements EventHandler<Object> {

    @Override
    public Object execute(ChanjetMsg<Object> chanjetMsg) {
        return null;
    }
}
