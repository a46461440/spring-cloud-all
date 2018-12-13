package com.zxc.product.service.impl;

import com.zxc.product.domain.po.ProductInfo;
import com.zxc.product.enums.ProductStatusEnum;
import com.zxc.product.repository.ProductInfoRepository;
import com.zxc.product.service.ProductService;
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
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }
}
