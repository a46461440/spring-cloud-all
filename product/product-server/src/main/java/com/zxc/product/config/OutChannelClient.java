package com.zxc.product.config;

import com.zxc.product.message.ProductStockSenderClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.stereotype.Component;

/**
 * @author Zhou RunMing
 * @Date 2018-12-18
 */
@Component
@EnableBinding({ProductStockSenderClient.class})
public class OutChannelClient {

}
