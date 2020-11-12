package com.chanjet.changsha.bank.pay.dao;

import com.chanjet.changsha.bank.pay.entity.Merchant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: zsc
 * @create: 2020/11/6 2:17 下午
 **/
@Repository
public interface MerchantDao extends JpaRepository<Merchant, Long> {
    Merchant findMerchantByMerchanId(String merchanId);

    Page<Merchant> findMerchantsByOrgId(String orgId, Pageable pageable);
}
