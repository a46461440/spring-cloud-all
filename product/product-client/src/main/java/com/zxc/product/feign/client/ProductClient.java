package com.zxc.product.feign.client;

import com.zxc.product.domain.ProductInfo;
import com.zxc.product.domain.ProductStockInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Feign客户端
 *
 * @author Zhou RunMing
 * @date 2018/12/16
 */
@FeignClient(value = "product")
@Component
public interface ProductClient {

    @GetMapping("/msg")
    String productMsg();

    @PostMapping("/product/listForOrder")
    List<ProductInfo> getProductListForOrder(@RequestBody List<String> productIdList);

    @PostMapping("/product/stock/decrease")
    void decreaseStock(@RequestBody List<ProductStockInfo> list);

}