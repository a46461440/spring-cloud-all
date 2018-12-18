package com.zxc.product.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author Zhou RunMing
 * @Date 2018-12-18
 */
public interface ProductStockSenderClient {

    String PRODUCT="product_stock";

    @Output(ProductStockSenderClient.PRODUCT)
    MessageChannel output();

}
