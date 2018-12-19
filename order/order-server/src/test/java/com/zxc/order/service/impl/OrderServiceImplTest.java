package com.zxc.order.service.impl;

import com.zxc.order.OrderApplicationTests;
import com.zxc.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
@Slf4j
public class OrderServiceImplTest extends OrderApplicationTests{

    @Autowired
    private OrderServiceImpl orderService;

    @Test
    public void testRedisPipeline() {
        this.orderService.getProductInfoByIds(Arrays.asList("product_stock_164103465734242708")).forEach(e ->{
            log.info(e.toString());
        });
    }

}