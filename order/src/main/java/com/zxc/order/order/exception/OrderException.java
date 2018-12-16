package com.zxc.order.order.exception;

import com.zxc.order.order.menus.OrderBindingResultEnum;

/**
 * 订单异常类,继承自{@link RuntimeException}
 *
 * @author Zhou RunMing
 * @date 2018/12/16
 */
public class OrderException extends RuntimeException {

    private Integer code;

    public OrderException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public OrderException(OrderBindingResultEnum orderBindingResultEnum) {
        super(orderBindingResultEnum.getMessage());
        this.code = orderBindingResultEnum.getCode();
    }
}
