package com.zxc.product.repository;

import com.zxc.product.domain.po.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Zhou RunMing
 * @Date 2018-12-13
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer>{

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryList);

}
