package com.chanjet.changsha.bank.pay.controller;

import com.chanjet.changsha.bank.pay.annotation.ApiRestController;
import com.chanjet.changsha.bank.pay.annotation.NeedToken;
import com.chanjet.changsha.bank.pay.common.ChanjetValueResult;
import com.chanjet.changsha.bank.pay.dto.KeyDto;
import com.chanjet.changsha.bank.pay.dto.MerchantDto;
import com.chanjet.changsha.bank.pay.dto.MerchantSaveDto;
import com.chanjet.changsha.bank.pay.entity.BookEntity;
import com.chanjet.changsha.bank.pay.entity.Page;
import com.chanjet.changsha.bank.pay.entity.PrivateKey;
import com.chanjet.changsha.bank.pay.entity.PublicKey;
import com.chanjet.changsha.bank.pay.exception.ErrorParamsRuntimeException;
import com.chanjet.changsha.bank.pay.service.MerchantService;
import com.chanjet.changsha.bank.pay.utils.FileUtil;
import com.chanjet.openapi.sdk.java.exception.ChanjetApiException;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

/**
 * 商户上传必要信息
 *
 * @author: zsc
 * @create: 2020/11/5 3:06 下午
 **/
@ApiRestController
@RestController
@RequestMapping("merchant")
@Log4j2
public class MerchantController {

    @Autowired
    MerchantService merchantService;

    /**
     * 公钥上传
     *
     * @param file 公钥文件
     * @return
     * @throws IOException
     */
    @PostMapping("/uploadPublicKey")
    @NeedToken
    public ChanjetValueResult<KeyDto> uploadPublicKey(MultipartFile file) throws IOException {
        File tmpFile = getFile(file);
        file.transferTo(tmpFile);

        PublicKey publicKey = merchantService.uploadPublicKey(tmpFile, file.getOriginalFilename());

        Files.delete(tmpFile.toPath());
        KeyDto keyDto = KeyDto.builder()
                .fileName(publicKey.getFileName())
                .id(publicKey.getId())
                .build();
        return ChanjetValueResult.success(keyDto);
    }

    /**
     * 私钥上传
     *
     * @param file 私钥文件
     * @param pwd  私钥秘钥
     * @return
     * @throws IOException
     */

    @PostMapping("/uploadPrivateKey")
    @NeedToken
    public ChanjetValueResult<KeyDto> uploadPrivateKey(MultipartFile file, String pwd, String merchantId) throws IOException {
        File tmpFile = getFile(file);
        file.transferTo(tmpFile);

        PrivateKey privateKey = merchantService.uploadPrivateKey(tmpFile, pwd, merchantId, file.getOriginalFilename());

        Files.delete(tmpFile.toPath());
        KeyDto keyDto = KeyDto.builder()
                .fileName(privateKey.getFileName())
                .id(privateKey.getId())
                .build();
        return ChanjetValueResult.success(keyDto);
    }

    /**
     * 根据id查询详情
     *
     * @param id
     * @return
     */
    @GetMapping("/findMerchantById/{id}")
    @NeedToken
    public ChanjetValueResult<MerchantDto> findMerchantById(@PathVariable("id") Long id) {
        return ChanjetValueResult.success(merchantService.queryById(id));
    }

    /**
     * 获取列表
     *
     * @param token
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findMerchantById/{page}/{size}")
    @NeedToken
    public ChanjetValueResult<Page<MerchantDto>> findMerchantList(@CookieValue("token") String token, @PathVariable int page, @PathVariable int size) {
        return ChanjetValueResult.success(merchantService.list(token, size, page));
    }

    /**
     * 保存商户信息
     *
     * @param merchantSaveDto
     * @param token
     * @return
     * @throws ChanjetApiException
     */
    @PostMapping("/saveMerchant")
    @NeedToken
    public ChanjetValueResult<Boolean> saveMerchant(@RequestBody MerchantSaveDto merchantSaveDto, @CookieValue("token") String token) throws ChanjetApiException {
        return ChanjetValueResult.success(merchantService.add(token, merchantSaveDto));
    }

    /**
     * 获取账套列表
     *
     * @param token
     * @return
     * @throws ChanjetApiException
     */
    @GetMapping("/getBookList")
    @NeedToken
    public ChanjetValueResult<List<BookEntity>> saveMerchant(@CookieValue("token") String token) throws ChanjetApiException {
        return ChanjetValueResult.success(merchantService.getBookList(token));
    }


    private File getFile(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (StringUtils.isEmpty(originalFilename)) {
            throw new ErrorParamsRuntimeException("file is empty");
        }
        return new File(FileUtil.getTmpDir(), UUID.randomUUID().toString());
    }

}
