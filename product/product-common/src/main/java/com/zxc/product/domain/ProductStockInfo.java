package com.zxc.product.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 扣减库存信息
 *
 * @author Zhou RunMing
 * @date 2018/12/16
 */
@Data
public class ProductStockInfo implements Serializable{

    private String productId;

    private Integer productQuantity;

}
