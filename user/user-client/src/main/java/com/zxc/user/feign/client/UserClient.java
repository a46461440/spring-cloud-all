package com.zxc.user.feign.client;

import com.zxc.user.domain.po.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
}

class UserClientFallback implements UserClient{

    @Override
    public List<UserInfo> getUserInfoByName(String name) {
        return null;
    }
}