package com.zxc.product.service.impl;

import com.zxc.product.ProductApplicationTests;
import com.zxc.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static org.junit.Assert.*;

@Component
@Slf4j
public class CategoryServiceImplTest extends ProductApplicationTests {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void findByCategoryTypeIn() throws Exception {
        this.categoryService.findByCategoryTypeIn(Arrays.asList(11, 22))
                .forEach(category -> this.log.info(category.toString()));
    }

}