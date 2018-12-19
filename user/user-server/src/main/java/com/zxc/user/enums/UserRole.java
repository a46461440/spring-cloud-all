package com.zxc.user.enums;

import lombok.Getter;

/**
 * @author Zhou RunMing
 * @Date 2018-12-19
 */
@Getter
public enum UserRole {

    BUYER(1, "买家"),
    SELLER(2, "卖家");

    private Integer code;

    private String roleName;

    UserRole(Integer code, String roleName) {
        this.code = code;
        this.roleName = roleName;
    }
}
