package com.zxc.user.service.impl;

import com.zxc.user.domain.po.UserInfo;
import com.zxc.user.repository.UserInfoRepository;
import com.zxc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Zhou RunMing
 * @Date 2018-12-19
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo findByOpenid(String openid) {
        return this.userInfoRepository.findByOpenid(openid);
    }
}
