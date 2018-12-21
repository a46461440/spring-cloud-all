package com.zxc.order.menus;

import lombok.Getter;

/**
 * 订单参数绑定枚举
 *
 * @author Zhou RunMing
 * @date 2018/12/16
 */
@Getter
public enum OrderResultEnum {

    PARAM_ERROR(1, "参数错误"),
    CAR_EMPTY(2, "购物车为空"),
    ORDER_NOT_EXITS(3, "订单不存在"),
    ORDER_STATUS_ERROR(4, "订单状态错误"),
    ORDER_HAS_BEEN_CHANGE(5, "订单已被修改"),
    ORDER_DETAIL_NOT_EXITS(6, "订单详情不存在"),
    PRODUCT_SERVER_ERROR(7, "商品服务调用出错"),
    ;

    private Integer code;

    private String message;

    OrderResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
