package com.zxc.user.domain.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Zhou RunMing
 * @Date 2018-12-19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo implements Serializable {

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

    private List<UserRoleGroupRelation> userRoleGroupRelationList;

}
