package com.zxc.product.service;

import com.zxc.product.domain.po.ProductCategory;

import java.util.List;

/**
 * 商品类目service
 *
 * @author Zhou RunMing
 * @date 2018/12/13
 */
public interface CategoryService {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryList);

}
