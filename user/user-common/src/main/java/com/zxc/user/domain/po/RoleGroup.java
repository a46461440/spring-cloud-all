package com.zxc.user.domain.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * RoleGroup实体
 *
 * @author Zhou RunMing
 * @date 2019/1/16
 */
@Data
public class RoleGroup  implements Serializable {

    private int nRgid;

    private int nGid;

    private String cGroupName;

    private int nRid;

    private Date dCreateDate;

    private Date dUpdateDate;

//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "roleGroup")
//    private UserRoleGroupRelation userRoleGroupRelation;

    private List<Role> roleList;
}
