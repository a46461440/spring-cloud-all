package com.zxc.apigateway.jwtsecurity;

import com.zxc.user.domain.po.*;
import com.zxc.apigateway.feign.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        //以前的写法
//        List<UserInfoWithRole> roleList = this.userClient.findRoleByUserId("1");
//        UserInfo userInfo = null;
//        if (userInfoList != null && userInfoList.size() == 1) {
//            userInfo = userInfoList.get(0);
//        }
//        if (userInfo != null) {
//            UserPrinciple userPrinciple = UserPrinciple.build(userInfo, roleList.stream()
//            .map(userInfoWithRole -> userInfoWithRole.getRoles()).collect(Collectors.toList()));
//            return userPrinciple;
//        }
        if (userInfoList != null && userInfoList.size() > 0) {
            UserInfo userInfo = userInfoList.get(0);
            List<String> roles = Collections.emptyList();
            for (UserRoleGroupRelation userRoleGroupRelation : userInfo.getUserRoleGroupRelationList()) {
                for (RoleGroup roleGroup : userRoleGroupRelation.getRoleGroupList()) {
                    for (Role role : roleGroup.getRoleList()) {
                        roles.add(role.getCRole());
                    }
                }
            }

            UserPrinciple userPrinciple = UserPrinciple.build(userInfo, roles);
            return userPrinciple;
        }
        return null;
    }
}
