package com.chanjet.changsha.bank.pay.controller;

import com.chanjet.changsha.bank.pay.annotation.ApiRestController;
import com.chanjet.changsha.bank.pay.common.Constants;
import com.chanjet.changsha.bank.pay.common.MsgType;
import com.chanjet.changsha.bank.pay.config.AppConfig;
import com.chanjet.changsha.bank.pay.event.ChanjetMsg;
import com.chanjet.changsha.bank.pay.event.MsgEvent;
import com.chanjet.changsha.bank.pay.event.content.AppTicketContent;
import com.chanjet.changsha.bank.pay.event.content.TempAuthCodeContent;
import com.chanjet.changsha.bank.pay.pojo.ChanjetEncryptMsg;
import com.chanjet.changsha.bank.pay.utils.AESUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * 接收消息的controller
 *
 * @author: zsc
 * @create: 2020/11/5 4:57 下午
 **/
@Log4j2
@ApiRestController
@RestController
@RequestMapping("message")
public class MessageController {
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    private AppConfig appConfig;

    @PostMapping("/chanjet/receive")
    public Object receive(@RequestBody ChanjetEncryptMsg chanjetEncryptMsg) {
        Map<String, String> responseMap = new HashMap<>();
        try {
            String encryptMsg = chanjetEncryptMsg.getEncryptMsg();
            log.info("解密前的消息{}", encryptMsg);
            String msg = AESUtils.aesDecrypt(encryptMsg, appConfig.getEncryptKey());
            log.info("解密后消息{}", msg);

            Map map = new Gson().fromJson(msg, Map.class);
            String msgType = (String) map.get(Constants.MSG_TYPE);

            //appKey不匹配返回fail
            if (!appConfig.getAppKey().equals(map.get(Constants.APP_KEY))) {
                responseMap.put(Constants.RESULT, Constants.FAIL);
                return responseMap;
            }

            //直接返回执行结果
            return execute(msg, msgType);
        } catch (Exception e) {
            log.error("解析消息失败", e);
            responseMap.put(Constants.RESULT, Constants.FAIL);
            return responseMap;
        }
    }

    /**
     * 执行方法
     *
     * @param msg     消息体
     * @param msgType 消息类型
     * @return
     */
    private Object execute(String msg, String msgType) {
        Map<String, String> responseMap = new HashMap<>();
        switch (msgType) {
            case MsgType.APP_TICKET:
                Type appTicketType = new TypeToken<ChanjetMsg<AppTicketContent>>() {
                }.getType();
                ChanjetMsg<AppTicketContent> appTicketMsg = new Gson().fromJson(msg, appTicketType);
                applicationContext.publishEvent(new MsgEvent(applicationContext, appTicketMsg));
                responseMap.put(Constants.RESULT, Constants.SUCCESS);
                return responseMap;
            case MsgType.TEMP_AUTH_CODE:
                Type tempAuthCodeType = new TypeToken<ChanjetMsg<TempAuthCodeContent>>() {
                }.getType();
                ChanjetMsg<TempAuthCodeContent> tempAuthCodeMsg = new Gson().fromJson(msg, tempAuthCodeType);
                applicationContext.publishEvent(new MsgEvent(applicationContext, tempAuthCodeMsg));
                responseMap.put(Constants.RESULT, Constants.SUCCESS);
                return responseMap;
            default:
                responseMap.put(Constants.RESULT, Constants.SUCCESS);
                return responseMap;
        }
    }
}
