package com.zxc.product.enums;

import lombok.Getter;

/**
 * 结果异常
 *
 * @author Zhou RunMing
 * @date 2018/12/16
 */
@Getter
public enum ResultEnum {

    PRODUCT_NOT_EXIST(1, "商品不存在"),
    PRODUCT_STOCK_ERRPR(2, "库存不足"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
