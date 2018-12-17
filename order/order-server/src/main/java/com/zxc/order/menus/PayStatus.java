package com.zxc.order.menus;

import lombok.Getter;

/**
 * TODO...
 *
 * @author Zhou RunMing
 * @date 2018/12/16
 */
@Getter
public enum PayStatus {

    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功")
    ;

    private Integer code;

    private String message;

    PayStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
