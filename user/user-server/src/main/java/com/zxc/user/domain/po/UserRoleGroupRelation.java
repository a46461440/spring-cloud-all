package com.zxc.user.domain.po;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * UserRoleGroupRelation 实体
 *
 * @author Zhou RunMing
 * @date 2019/1/16
 */
@Table
@Entity
@Data
public class UserRoleGroupRelation implements Serializable {

    @Id
    @GeneratedValue
    private int nUrid;

    private int nUid;

    @Column(insertable = false, updatable = false)
    private int nGid;

    private Date dCreateDate;

    private Date dUpdateDate;
//    /**
//     * 其中optional为关联对象不能为空
//     */
//    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
//    @JoinColumn(name = "nUid", referencedColumnName = "id")
//    private UserInfo userInfo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "nGid", referencedColumnName = "nGid")
    private List<RoleGroup> roleGroupList;
}
