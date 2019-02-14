package com.zxc.product.repository;

import com.zxc.product.ProductTest;
import com.zxc.product.domain.po.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Zhou RunMing
 * @date 2019-2-14 14:20
 *
 * 测试{@link ProductInfoRepository}功能
 */
@Component
@Slf4j
public class ProductInfoRepositoryTest extends ProductTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void testProductInfoRepository() {
        List<ProductInfo> productInfoList = this.productInfoRepository.findByProductIdIn(Arrays.asList("157875196366160023"));
        for (ProductInfo productInfo : productInfoList) {
            this.log.info(productInfo.toString());
        }
    }

}