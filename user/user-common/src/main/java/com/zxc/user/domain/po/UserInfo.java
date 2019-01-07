package com.zxc.user.domain.po;

import lombok.Data;

import java.util.Date;

/**
 * @author Zhou RunMing
 * @Date 2018-12-19
 */
@Data
public class UserInfo {

    private String id;

    private String username;

    private String password;
    /**
     * '微信openid'
     */
    private String openid;
    /**
     * '1买家2卖家'
     */
    private Integer role;
    /**
     * '创建时间'
     */
    private Date createTime;
    /**
     * '修改时间'
     */
    private Date updateTime;

}
