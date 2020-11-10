package com.chanjet.changsha.bank.pay.service;

import com.chanjet.changsha.bank.pay.dto.MerchantDto;

import java.io.File;
import java.util.List;

/**
 * @author: zsc
 * @create: 2020/11/5 3:02 下午
 **/
public interface MerchantService {

    /**
     * 根据商户ID查询私钥
     *
     * @param merchantId
     * @return
     */
    String getPrivateKey(String merchantId);

    /**
     * 上传私钥
     *
     * @param file
     * @param password
     * @return
     */
    Long uploadPrivateKey(File file, String password);

    /**
     * 上传公钥
     *
     * @param file
     * @return
     */
    Long uploadPublicKey(File file);

    /**
     * 根据ID查询商户信息
     *
     * @param id
     * @return
     */
    MerchantDto queryById(Long id);

    /**
     * 查询商户列表
     *
     * @param userId
     * @return
     */
    List<MerchantDto> list(String userId);

    /**
     * 添加商户
     *
     * @param merchantDto
     * @return
     */
    MerchantDto add(MerchantDto merchantDto);

    /**
     * 更新商户
     *
     * @param id
     * @param merchantDto
     * @return
     */
    MerchantDto update(Long id, MerchantDto merchantDto);

    /**
     * 删除商户
     *
     * @param id
     * @return
     */
    MerchantDto delete(Long id);

}
