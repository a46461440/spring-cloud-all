package com.zxc.product.service;

import com.zxc.product.ProductApplicationTests;
import com.zxc.product.domain.dto.ProductStockInfo;
import com.zxc.product.domain.po.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@Slf4j
@ComponentScan
public class ProductServiceTest extends ProductApplicationTests{

    @Autowired
    private ProductService productService;

    @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> list = productService.findUpAll();
        list.forEach(productInfo -> System.out.printf(productInfo.toString()));
    }

    @Test
    public void findByProductIdIn(){
        List<ProductInfo> list = this.productService.findByProductIdIn(Arrays.asList("157875227953464069", "157875196366160023"));
        Assert.assertTrue(list != null && list.size() > 0);
        list.forEach(productInfo -> this.log.info(productInfo.toString()));
    }

    @Test
    public void decreaseStock() {
        ProductStockInfo productStockInfo = new ProductStockInfo();
        productStockInfo.setProductQuantity(1);
        productStockInfo.setProductId("164103465734242708");
        this.productService.decreaseStock(Arrays.asList(productStockInfo));
    }

}