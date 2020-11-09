package com.chanjet.changsha.bank.pay.service.impl;

import com.chanjet.changsha.bank.pay.dto.MerchantDto;
import com.chanjet.changsha.bank.pay.service.PayService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * @author: zsc
 * @create: 2020/11/5 4:56 下午
 **/
@Service
public class PayServiceImpl implements PayService {

    @Override
    public Long uploadPrivateKey(File file, String password) {
        return null;
    }

    @Override
    public Long uploadPublicKey(File file) {
        return null;
    }

    @Override
    public MerchantDto queryById(Long id) {
        return null;
    }

    @Override
    public List<MerchantDto> list(String userId) {
        return null;
    }

    @Override
    public MerchantDto add(MerchantDto merchantDto) {
        return null;
    }

    @Override
    public MerchantDto update(Long id, MerchantDto merchantDto) {
        return null;
    }

    @Override
    public MerchantDto delete(Long id) {
        return null;
    }
}
