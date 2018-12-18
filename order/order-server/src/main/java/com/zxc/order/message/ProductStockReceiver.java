package com.zxc.order.message;

import com.zxc.product.domain.ProductStockInfo;
import com.zxc.product.message.ProductStockClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Zhou RunMing
 * @Date 2018-12-18
 */
@Component
@Slf4j
public class ProductStockReceiver {

    private static final String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";

    @Autowired
    private RedisTemplate redisTemplate;

    @StreamListener(ProductStockClient.PRODUCT)
    public void getProductStock(List<ProductStockInfo> productStockInfoList) {
        productStockInfoList.forEach(productStockInfo -> this.log.info(productStockInfo.toString()));
        //存储到redis
        this.redisTemplate.executePipelined(new SessionCallback<String>() {
            @Nullable
            @Override
            public <K, V> String execute(RedisOperations<K, V> redisOperations) throws DataAccessException {
                productStockInfoList.forEach(productStockInfo -> {
                    redisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE, productStockInfo.getProductId()),
                            productStockInfo.getProductQuantity());
                });
                return null;
            }
        });

    }

}
