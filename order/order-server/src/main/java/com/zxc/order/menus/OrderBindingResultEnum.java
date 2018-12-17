package com.zxc.order.menus;

import lombok.Getter;

/**
 * 订单参数绑定枚举
 *
 * @author Zhou RunMing
 * @date 2018/12/16
 */
@Getter
public enum OrderBindingResultEnum {

    PARAM_ERROR(1, "参数错误"),
    CAR_EMPTY(2, "购物车为空"),
    ;

    private Integer code;

    private String message;

    OrderBindingResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
