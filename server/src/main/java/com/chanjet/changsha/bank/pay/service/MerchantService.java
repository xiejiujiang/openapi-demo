package com.chanjet.changsha.bank.pay.service;

import com.chanjet.changsha.bank.pay.dto.MerchantDto;
import com.chanjet.changsha.bank.pay.dto.MerchantSaveDto;
import com.chanjet.changsha.bank.pay.entity.BookEntity;
import com.chanjet.changsha.bank.pay.entity.Page;
import com.chanjet.changsha.bank.pay.entity.PrivateKey;
import com.chanjet.changsha.bank.pay.entity.PublicKey;
import com.chanjet.openapi.sdk.java.exception.ChanjetApiException;

import java.io.File;
import java.util.List;

/**
 * @author: zsc
 * @create: 2020/11/5 3:02 下午
 **/
public interface MerchantService {
    /**
     * 根据商户ID获取私钥字符串
     *
     * @param merchantId
     * @return
     */
    String getPrivateKey(String merchantId,String bookId);

    /**
     * 上传私钥
     *
     * @param file
     * @param password
     * @return
     */
    PrivateKey uploadPrivateKey(File file, String password,String merchantId);

    /**
     * 上传公钥
     *
     * @param file
     * @return
     */
    PublicKey uploadPublicKey(File file);

    /**
     * 根据ID查询商户信息
     *
     * @param id
     * @return
     */
    MerchantDto queryById(Long id);

    /**
     * 查询商户列表
     * @param token
     * @param size
     * @param page
     * @return
     */
    Page<MerchantDto> list(String token, int size, int page);

    /**
     * 添加商户
     *
     * @param merchantDto
     * @return
     */
    boolean add(String token,MerchantSaveDto merchantDto) throws ChanjetApiException;

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

    /**
     * 获取账套列表
     * @param token
     * @return
     * @throws ChanjetApiException
     */
    List<BookEntity> getBookList(String token) throws ChanjetApiException;


}
