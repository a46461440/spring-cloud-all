package com.zxc.product.service;

import com.zxc.product.domain.ProductStockInfo;
import com.zxc.product.domain.po.ProductInfo;

import java.util.List;

/**
 * 商品service
 *
 * @author Zhou RunMing
 * @date 2018/12/13
 */
public interface ProductService {

    /**
     * 查询所有在架商品列表
     */
    List<ProductInfo> findUpAll();

    List<ProductInfo> findByProductIdIn(List<String> productIdList);

    Integer decreaseStock(List<ProductStockInfo> list);

}
