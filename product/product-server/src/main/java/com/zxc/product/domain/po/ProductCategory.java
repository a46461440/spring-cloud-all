package com.zxc.product.domain.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Zhou RunMing
 * @Date 2018-12-13
 */
@Data
@Entity
@Table(name = "ProductCategory")
public class ProductCategory {

    @Id
    @GeneratedValue
    private Integer categoryId;
    /**
     * '类目名字'
     */
    private String categoryName;
    /**
     * '类目编号'
     */
    private Integer categoryType;
    /**
     * '创建时间'
     */
    private Date createTime;
    /**
     * '修改时间'
     */
    private Date updateTime;

}
