package com.zxc.product.service.impl;

import com.zxc.product.domain.po.ProductCategory;
import com.zxc.product.repository.ProductCategoryRepository;
import com.zxc.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO...
 *
 * @author Zhou RunMing
 * @date 2018/12/13
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryList) {
        return this.productCategoryRepository.findByCategoryTypeIn(categoryList);
    }
}
