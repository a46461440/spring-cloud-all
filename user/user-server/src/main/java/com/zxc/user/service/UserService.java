package com.zxc.user.service;

import com.zxc.user.domain.po.UserInfo;

/**
 * @author Zhou RunMing
 * @Date 2018-12-19
 */
public interface UserService {

    /**
     * 通过openid查询
     * @param openid
     * @return
     */
    UserInfo findByOpenid(String openid);

    UserInfo findByUsernameAndPassword(String username, String password);

}
