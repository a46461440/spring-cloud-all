package com.zxc.product.exception;

import com.zxc.product.enums.ResultEnum;

/**
 * Product服务自定义异常
 *
 * @author Zhou RunMing
 * @date 2018/12/16
 */
public class ProductException extends RuntimeException {

    private Integer code;

    public ProductException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ProductException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
