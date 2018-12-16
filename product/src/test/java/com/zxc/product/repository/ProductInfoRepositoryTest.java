package com.zxc.product.repository;

import com.zxc.product.domain.dto.ProductStockInfo;
import com.zxc.product.domain.po.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j

public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void findByProductStatus() throws Exception {
        List<ProductInfo> list = this.productInfoRepository.findByProductStatus(0);
        list.forEach(profuctInfo ->
                this.log.info(profuctInfo.toString())
        );
    }

    @Test
    public void testFindProductByProductId() {
        List<ProductInfo> list = this.productInfoRepository.findByProductIdIn(Arrays.asList("157875227953464069", "157875196366160023"));
        Assert.assertTrue(list != null && list.size() > 0);
        list.forEach(productInfo -> this.log.info(productInfo.toString()));
    }

    @Test
    public void testDecreaseStock() {
        this.productInfoRepository.updateProductStock(982, 1, Arrays.asList("164103465734242708"));
    }

}