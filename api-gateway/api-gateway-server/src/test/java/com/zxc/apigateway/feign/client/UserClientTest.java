package com.zxc.apigateway.feign.client;

import com.zxc.apigateway.ApiGatewayApplicationTests;
import com.zxc.user.domain.po.UserInfoWithRole;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class UserClientTest extends ApiGatewayApplicationTests{

    @Autowired
    private UserClient userClient;

    @Test
    public void testUserClient() {
        List<UserInfoWithRole> list = this.userClient.findRoleByUserId("1");
    }

}