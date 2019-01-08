package com.zxc.user.domain.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Zhou RunMing
 * @Date 2018-12-19
 */
@Data
@Entity(name = "userInfo")
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    @Id
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
