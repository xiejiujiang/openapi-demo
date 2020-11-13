package com.chanjet.changsha.bank.pay.service.impl;

import com.chanjet.changsha.bank.pay.command.builder.CsBankCommandBuilder;
import com.chanjet.changsha.bank.pay.config.AppConfig;
import com.chanjet.changsha.bank.pay.dao.MerchantDao;
import com.chanjet.changsha.bank.pay.dao.PrivateKeyDao;
import com.chanjet.changsha.bank.pay.dao.PublicKeyDao;
import com.chanjet.changsha.bank.pay.dao.UserDao;
import com.chanjet.changsha.bank.pay.dto.MerchantDto;
import com.chanjet.changsha.bank.pay.dto.MerchantSaveDto;
import com.chanjet.changsha.bank.pay.entity.*;
import com.chanjet.changsha.bank.pay.exception.*;
import com.chanjet.changsha.bank.pay.pojo.OrderPayResponse;
import com.chanjet.changsha.bank.pay.pojo.PushMerchantContent;
import com.chanjet.changsha.bank.pay.pojo.PushMerchantResponse;
import com.chanjet.changsha.bank.pay.service.MerchantService;
import com.chanjet.changsha.bank.pay.spi.chanjet.ChanjetSpi;
import com.chanjet.changsha.bank.pay.spi.csbank.OrderPay;
import com.chanjet.changsha.bank.pay.utils.SignUtil;
import com.chanjet.openapi.sdk.java.exception.ChanjetApiException;
import com.chanjet.openapi.sdk.java.response.hsy.FindByEnterpriseIdResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: zsc
 * @create: 2020/11/5 4:56 下午
 **/
@Service
@Log4j2
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    private MerchantDao merchantDao;
    @Autowired
    private PrivateKeyDao privateKeyDao;
    @Autowired
    PublicKeyDao publicKeyDao;
    @Autowired
    UserDao userDao;
    @Autowired
    ChanjetSpi chanjetSpi;
    @Autowired
    AppConfig appConfig;
    @Autowired
    private CsBankCommandBuilder csBankCommandBuilder;

    @Override
    public String getPrivateKey(String merchantId,String bookId) {
        Merchant merchant = merchantDao.findMerchantByMerchanIdAndBookId(merchantId,bookId);
        PrivateKey privateKey = privateKeyDao.findById(merchant.getPrivateKeyId()).orElse(new PrivateKey());
        return privateKey.getPrivateKeyString();
    }

    @Override
    public PrivateKey uploadPrivateKey(File file, String password, String merchantId) {
        String privateKeyString = "";
        try {
            privateKeyString = SignUtil.getPrivateKeyString(file, password);
        } catch (GeneralSecurityException | IOException e) {
            log.error(e);
            throw new KeyErrorException("私钥和秘钥不匹配");
        }
        PrivateKey privateKey = PrivateKey.builder()
                .fileName(file.getName())
                .privateKeyString(privateKeyString)
                .key(password)
                .build();
        OrderPay orderPay = csBankCommandBuilder.create(OrderPay.class);
        orderPay.setBackUrl("https://www.baidu.com");
        orderPay.setECustId(merchantId);
        orderPay.setUrl(appConfig.getOrderPayUrl());
        orderPay.setPayMethod("7");
        orderPay.setCardNo("123456789012345678");
        orderPay.setMerchOrder(System.currentTimeMillis() + "");
        orderPay.setOrderAmount("0.01");
        orderPay.setRemark("测试");
        orderPay.setPrivateKeyString(privateKeyString);
        try {
            OrderPayResponse orderPayResponse = orderPay.excute();
            String msg = orderPayResponse.getMsg();
           if (msg.contains("验签失败") || msg.contains("不存在")){
               throw new KeyErrorException("您上传的私钥与商户ID【"+merchantId+"】不匹配，请检查数据重新上传");
           }
        } catch (ApiFailtureException | BadNetworkExcepiton e) {
            log.error(e);
            throw new KeyErrorException("系统错误，请稍后重试");
        }

        return privateKeyDao.save(privateKey);
    }

    @Override
    public PublicKey uploadPublicKey(File file) {
        try {
            String publicKeyString = SignUtil.getPublicKeyString(file);
            PublicKey publicKey = PublicKey.builder()
                    .fileName(file.getName())
                    .key(publicKeyString)
                    .build();
            return publicKeyDao.save(publicKey);
        } catch (GeneralSecurityException | IOException e) {
            log.error(e);
            throw new KeyErrorException("公钥错误");
        }
    }

    @Override
    public MerchantDto queryById(Long id) {
        Merchant merchant = merchantDao.findById(id).orElse(new Merchant());
        return getMerchantDto(merchant);
    }

    private MerchantDto getMerchantDto(Merchant merchant) {
        String privateFileName = privateKeyDao.findById(merchant.getPrivateKeyId()).orElse(new PrivateKey()).getFileName();
        String publicFileName = publicKeyDao.findById(merchant.getPrivateKeyId()).orElse(new PublicKey()).getFileName();
        MerchantDto merchantDto = new MerchantDto();
        BeanUtils.copyProperties(merchant, merchantDto);
        merchantDto.setPrivateKeyFileName(privateFileName);
        merchantDto.setPublicKeyFileName(publicFileName);
        return merchantDto;
    }

    @Override
    public Page list(String token, int size, int page) {
        User user = userDao.findUserByToken(token);
        if (user == null){
            throw new NoTokenException("token不合法");
        }
        String orgId = user.getOrgId();
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageable = PageRequest.of(page, size, sort);

        org.springframework.data.domain.Page<Merchant> merchantPage = merchantDao.findMerchantsByOrgId(orgId, pageable);
        List collect = merchantPage.getContent().stream()
                .map(this::getMerchantDto).collect(Collectors.toList());

        return Page.builder()
                .totalCount(merchantPage.getTotalElements())
                .totalPages(merchantPage.getTotalPages())
                .currentPage(merchantPage.getPageable().getPageNumber())
                .resultList(collect)
                .build();
    }

    @Override
    public boolean add(String token,MerchantSaveDto merchantDto) throws ChanjetApiException {
        Merchant merchant = Merchant.builder()
                .build();
        BeanUtils.copyProperties(merchantDto,merchant);
        User user = userDao.findUserByToken(token);
        merchant.setOrgId(user.getOrgId());

        Merchant merchantByMerchanIdAndBookId = merchantDao.findMerchantByMerchanIdAndBookId(merchantDto.getMerchanId(), merchantDto.getBookId());
        if (null != merchantByMerchanIdAndBookId) {
            throw new MerchantIsBindException("该账套已绑定过商户ID【"+ merchantDto.getMerchanId() +"】，可到列表查看。");
        }

        Merchant merchantSave = merchantDao.saveAndFlush(merchant);

        //推送商户消息到支付系统
        PushMerchantContent pushMerchantContent = PushMerchantContent.getInstance(merchantSave, appConfig.getAppKey());

        PushMerchantResponse pushMerchantResponse = chanjetSpi.pushMerchant(token, pushMerchantContent);
        return pushMerchantResponse.isStatus();
    }

    @Override
    public List<BookEntity> getBookList(String token) throws ChanjetApiException {
        FindByEnterpriseIdResponse findByEnterpriseIdResponse = chanjetSpi.findByEnterpriseId(token, "BINDING_TO_THIRD_PLATFORM");

        List<BookEntity> bookEntities = findByEnterpriseIdResponse.stream()
                .map(tenant -> {
                    BookEntity bookEntity = new BookEntity();
                    BeanUtils.copyProperties(tenant.getTenant(), bookEntity);
                    return bookEntity;
                }).collect(Collectors.toList());
        return bookEntities;
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
