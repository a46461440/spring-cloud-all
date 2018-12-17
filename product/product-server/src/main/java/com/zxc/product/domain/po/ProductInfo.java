package com.zxc.product.domain.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Zhou RunMing
 * @Date 2018-12-13
 */
@Table(name = "ProductInfo")
@Data
@Entity
public class ProductInfo  implements Serializable {

    @Id
    private String productId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * '单价'
     */
    private BigDecimal productPrice;
    /**
     * '库存'
     */
    private Integer productStock;
    /**
     * '描述'
     */
    private String productDescription;
    /**
     * '小图'
     */
    private String productIcon;
    /**
     * '商品状态,0正常1下架'
     */
    private Integer productStatus;
    /**
     * 类目编号
     */
    private Integer categoryType;
    /**
     * '创建时间'
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

}
