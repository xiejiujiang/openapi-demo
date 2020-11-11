package com.chanjet.changsha.bank.pay.service.impl;

import com.chanjet.changsha.bank.pay.dao.MerchantDao;
import com.chanjet.changsha.bank.pay.dao.PrivateKeyDao;
import com.chanjet.changsha.bank.pay.dto.MerchantDto;
import com.chanjet.changsha.bank.pay.entity.Merchant;
import com.chanjet.changsha.bank.pay.entity.PrivateKey;
import com.chanjet.changsha.bank.pay.entity.PublicKey;
import com.chanjet.changsha.bank.pay.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * @author: zsc
 * @create: 2020/11/5 4:56 下午
 **/
@Service
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    private MerchantDao merchantDao;
    @Autowired
    private PrivateKeyDao privateKeyDao;

    @Override
    public String getPrivateKey(String merchantId) {
        Merchant merchant = merchantDao.findMerchantByMerchanId(merchantId);
        PrivateKey privateKey = privateKeyDao.findById(merchant.getPrivateKeyId()).orElse(new PrivateKey());
        return privateKey.getPrivateKeyString();
    }

    @Override
    public PrivateKey uploadPrivateKey(File file, String password) {
        return null;
    }

    @Override
    public PublicKey uploadPublicKey(File file) {
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
