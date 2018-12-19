package com.zxc.product.service.impl;

import com.zxc.product.domain.ProductStockInfo;
import com.zxc.product.domain.po.ProductInfo;
import com.zxc.product.enums.ProductStatusEnum;
import com.zxc.product.enums.ResultEnum;
import com.zxc.product.exception.ProductException;
import com.zxc.product.message.ProductStockSenderClient;
import com.zxc.product.repository.ProductInfoRepository;
import com.zxc.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private ProductStockSenderClient productStockSenderClient;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfo> findByProductIdIn(List<String> productIdList) {
        return this.productInfoRepository.findByProductIdIn(productIdList);
    }

    /**
     * 扣库存并且发送当前商品信息到MQ
     * @param list
     */
    @Transactional
    @Override
    public void decreaseStock(List<ProductStockInfo> list) {
        //DB扣库存
        List<ProductInfo> productStockInfoList = this.decreaseStockInDB(list);
        //Redis存库存
        this.productStockSenderClient.output().send(MessageBuilder.withPayload(productStockInfoList).build());
    }

    /**
     * 在db中扣除库存
     * @param list 购物车信息
     * @return
     */
    public List<ProductInfo> decreaseStockInDB(List<ProductStockInfo> list) {
        List<ProductInfo> productInfoList = new ArrayList<>(list.size() * 4 / 3);
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
            productInfo.setProductStock(afterDecrease);
            productInfoList.add(productInfo);
        });
        return productInfoList;
    }
}
