package com.chanjet.openapi.demo.dao;

import com.chanjet.openapi.demo.entity.Merchant;
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
    Merchant findMerchantByMerchanIdAndBookId(String merchanId,String bookId);

    Page<Merchant> findMerchantsByOrgId(String orgId, Pageable pageable);
}
