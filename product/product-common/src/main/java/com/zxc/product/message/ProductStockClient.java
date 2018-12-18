package com.zxc.product.message;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * 提供给外部服务使用的product服务库存队列
 *
 * @author Zhou RunMing
 * @Date 2018-12-18
 */
public interface ProductStockClient {

    String PRODUCT="product_stock";

    @Input(ProductStockClient.PRODUCT)
    SubscribableChannel input();

}
