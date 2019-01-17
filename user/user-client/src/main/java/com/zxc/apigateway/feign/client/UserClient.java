package com.zxc.apigateway.feign.client;

import com.zxc.user.domain.po.UserInfo;
import com.zxc.user.domain.po.UserInfoWithRole;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;

/**
 * user feign客户端
 *
 * @author Zhou RunMing
 * @date 2019/1/7
 */
@FeignClient(value = "user", fallback = UserClientFallback.class)
@Component
public interface UserClient {

    @GetMapping("/user/list/{name}")
    List<UserInfo> getUserInfoByName(@PathVariable("name") String name);

    @GetMapping("/user/role/{userId}")
    List<UserInfoWithRole> findRoleByUserId(@PathVariable("userId") String userId);
}

class UserClientFallback implements UserClient{

    @Override
    public List<UserInfo> getUserInfoByName(String name) {
        return null;
    }

    @Override
    public List<UserInfoWithRole> findRoleByUserId(String userId) {
        return Arrays.asList();
    }
}