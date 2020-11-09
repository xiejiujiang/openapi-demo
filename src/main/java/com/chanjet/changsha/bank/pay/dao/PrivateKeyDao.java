package com.chanjet.changsha.bank.pay.dao;

import com.chanjet.changsha.bank.pay.entity.PrivateKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: zsc
 * @create: 2020/11/6 2:17 下午
 **/
@Repository
public interface PrivateKeyDao extends JpaRepository<PrivateKey, Long> {

}
