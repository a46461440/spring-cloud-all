package com.zxc.apigateway.user;

import com.zxc.apigateway.ApiGatewayApplicationTests;
import com.zxc.user.domain.po.UserInfo;
import com.zxc.user.feign.client.UserClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * TODO...
 *
 * @author Zhou RunMing
 * @date 2019/1/7
 */
@Component
@Slf4j
public class FeignClientTest extends ApiGatewayApplicationTests {

    @Autowired
    private UserClient userClient;

    @Test
    public void testGetList() {
        List<UserInfo> list = this.userClient.getUserInfoByName("zxc");
        this.log.info(list.size() + "");
    }

}
