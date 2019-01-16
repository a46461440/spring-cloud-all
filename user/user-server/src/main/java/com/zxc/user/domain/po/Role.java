package com.zxc.user.domain.po;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * role 实体
 *
 * @author Zhou RunMing
 * @date 2019/1/16
 */
@Data
@Table
@Entity
public class Role implements Serializable{

    @Id
    @GeneratedValue
    private Integer nRid;

    private String cRole;

    private String cRoleDesc;

    private Date dUpdateDate;

    private Date dCreateDate;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "nRid")
//    private RoleGroup roleGroup;

}
