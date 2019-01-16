package com.zxc.apigateway.jwtsecurity;

import com.zxc.user.domain.po.UserInfo;
import com.zxc.apigateway.feign.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * find a record from user database to build a {@link UserDetails}for authentication
 *
 * @author Zhou RunMing
 * @date 2019/1/7
 */
@Component
public class UserDetailImpl implements UserDetailsService{

    @Autowired
    private UserClient userClient;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<UserInfo> userInfoList = this.userClient.getUserInfoByName(s);
        List<String> roleList = this.userClient.findRoleByUserId(1);
        UserInfo userInfo = null;
        if (userInfoList != null && userInfoList.size() == 1) {
            userInfo = userInfoList.get(0);
        }
        if (userInfo != null) {
            UserPrinciple userPrinciple = UserPrinciple.build(userInfo, roleList);
            return userPrinciple;
        }
        return null;
    }
}
