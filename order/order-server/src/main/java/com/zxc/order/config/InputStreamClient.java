package com.zxc.order.config;

import com.zxc.product.message.ProductStockClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.stereotype.Component;

/**
 * @author Zhou RunMing
 * @Date 2018-12-18
 */
@Component
@EnableBinding({ProductStockClient.class})
public class InputStreamClient {
}
