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

    SUCCESS(200, "成功"),
    USER_NOT_FOUNT(404, "用户不存在"),
    ROLE_ERROR(500, "角色错误");

    private Integer code;

    private String msg;

    ResultVOStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
