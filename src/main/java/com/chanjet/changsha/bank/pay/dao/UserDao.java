package com.chanjet.changsha.bank.pay.dao;

import com.chanjet.changsha.bank.pay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: zsc
 * @create: 2020/11/6 2:17 下午
 **/
@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User findUserByToken(String token);

    User findUserByOrgIdAndUserId(String orgId, String userId);
}
