package com.zxc.user.repository;

import com.zxc.user.domain.po.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Zhou RunMing
 * @Date 2018-12-19
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

    UserInfo findByOpenid(String openid);

    UserInfo findByUsernameAndPassword(String username, String password);

    List<UserInfo> findByUsername(String username);
}
