package com.zxc.apigateway.feign.client;

import com.zxc.apigateway.ApiGatewayApplicationTests;
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
        List<String> list = this.userClient.findRoleByUserId(1);
        for (String s : list) {
            this.log.info(s);
        }
    }

}