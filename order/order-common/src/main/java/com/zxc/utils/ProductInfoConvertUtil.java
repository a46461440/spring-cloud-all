package com.zxc.utils;

import com.zxc.product.domain.ProductInfo;

/**
 * {@link ProductInfo}信息转化工具
 *
 * @author Zhou RunMing
 * @Date 2018-12-19
 */
public class ProductInfoConvertUtil {

    public static final String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";

    public static String getProductIdInCache(ProductInfo productInfo) {
        return String.format(PRODUCT_STOCK_TEMPLATE, productInfo.getProductId());
    }
    public static String getProductIdInCache(String productId) {
        return String.format(PRODUCT_STOCK_TEMPLATE, productId);
    }

}
