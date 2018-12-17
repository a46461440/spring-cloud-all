package com.zxc.product.enums;

import lombok.Getter;

/**
 * TODO...
 *
 * @author Zhou RunMing
 * @date 2018/12/13
 */
@Getter
public enum ResultVOStatus {

    SUCCESS(200, "成功");

    private Integer code;

    private String msg;

    ResultVOStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
