package com.zxc.order;

import com.zxc.product.domain.ProductInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Zhou RunMing
 * @Date 2019-2-14
 */
public class NormalTest {

    public static void main(String[] args) {
        List<ProductInfo> productInfoList = null;
        if (Optional.ofNullable(productInfoList).isPresent())
            for (ProductInfo productInfo : productInfoList) {
                System.out.println(productInfo);
            }
    }

}
