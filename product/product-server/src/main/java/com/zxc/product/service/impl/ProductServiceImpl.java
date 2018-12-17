package com.zxc.product.service.impl;

import com.zxc.product.domain.ProductStockInfo;
import com.zxc.product.domain.po.ProductInfo;
import com.zxc.product.enums.ProductStatusEnum;
import com.zxc.product.enums.ResultEnum;
import com.zxc.product.exception.ProductException;
import com.zxc.product.repository.ProductInfoRepository;
import com.zxc.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 商品service实现类
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

    @Override
    public List<ProductInfo> findByProductIdIn(List<String> productIdList) {
        return this.productInfoRepository.findByProductIdIn(productIdList);
    }

    @Override
    @Transactional
    public void decreaseStock(List<ProductStockInfo> list) {
        list.forEach(productStockInfo -> {
            Optional<ProductInfo> productInfoOptional =
                    this.productInfoRepository.findById(productStockInfo.getProductId());
            if (!productInfoOptional.isPresent()) {
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            ProductInfo productInfo = productInfoOptional.get();
            Integer afterDecrease = productInfo.getProductStock() - productStockInfo.getProductQuantity();
            if (afterDecrease < 0) {
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERRPR);
            }
            this.productInfoRepository.updateProductStock(productInfo.getProductStock(), productStockInfo.getProductQuantity(),
                    Arrays.asList(productStockInfo.getProductId()));
        });

    }
}
