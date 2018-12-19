package com.zxc.order.message;

import com.zxc.product.domain.ProductInfo;
import com.zxc.product.domain.ProductStockInfo;
import com.zxc.product.message.ProductStockClient;
import com.zxc.utils.ProductInfoConvertUtil;
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

    @Autowired
    private RedisTemplate redisTemplate;

    @StreamListener(ProductStockClient.PRODUCT)
    public void getProductStock(List<ProductInfo> productInfoList) {
        productInfoList.forEach(productInfo -> this.log.info(productInfo.toString()));
        //存储到redis
        this.redisTemplate.executePipelined(new SessionCallback<String>() {
            @Nullable
            @Override
            public <K, V> String execute(RedisOperations<K, V> redisOperations) throws DataAccessException {
                productInfoList.forEach(productInfo -> {
                    redisTemplate.opsForValue().set(ProductInfoConvertUtil.getProductIdInCache(productInfo.getProductId()),
                            productInfo);
                });
                return null;
            }
        });

    }

}
