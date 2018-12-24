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

    LOGIN_SUCCESS(200, "登录成功"),
    SUCCESS(200, "成功"),
    USER_NOT_FOUNT(404, "用户不存在"),
    ROLE_ERROR(500, "角色错误"),
    JWT_TOKEN_ERROR(600,"JwtToken解析错误");

    private Integer code;

    private String msg;

    ResultVOStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
