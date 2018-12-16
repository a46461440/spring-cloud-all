package com.zxc.order.order.menus;

import lombok.Getter;

/**
 * TODO...
 *
 * @author Zhou RunMing
 * @date 2018/12/16
 */
@Getter
public enum OrderStatus {
    NEW(0, "新订单"),
    FINISHED(1, "完成"),
    CANCEL(2, "取消");

    private Integer code;

    private String message;

    OrderStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
