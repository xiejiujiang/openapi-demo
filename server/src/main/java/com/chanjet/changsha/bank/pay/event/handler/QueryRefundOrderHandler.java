package com.chanjet.changsha.bank.pay.event.handler;

import com.chanjet.changsha.bank.pay.event.ChanjetMsg;
import com.chanjet.changsha.bank.pay.event.EventHandler;
import com.chanjet.changsha.bank.pay.event.content.QueryRefundOrderContent;
import org.springframework.stereotype.Component;

/**
 * @author: zsc
 * @create: 2020/11/11 11:07 上午
 **/
@Component
public class QueryRefundOrderHandler implements EventHandler<QueryRefundOrderContent> {

    @Override
    public Object execute(ChanjetMsg<QueryRefundOrderContent> chanjetMsg) {

        return chanjetMsg;
    }
}
