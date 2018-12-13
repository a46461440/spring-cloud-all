package com.zxc.product.repository;

import com.zxc.product.domain.po.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
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
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findByCategoryTypeIn() throws Exception {
        List<ProductCategory> list = this.productCategoryRepository.findByCategoryTypeIn(Arrays.asList(11, 12));
        list.forEach(category -> this.log.info(category.toString()));
    }

}