package com.zxc.order.domain.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 数据库OrderMaster表映射实体
 *
 * @author Zhou RunMing
 * @date 2018/12/16
 */
@Data
@Entity
@Table//(name = "OrderMaster")
public class OrderMaster {

    @Id
    private String orderId;
    /**
     * '买家名字'
     */
    private String buyerName;
    /**
     *  '买家电话'
     */
    private String buyerPhone;
    /**
     * '买家地址'
     */
    private String buyerAddress;
    /**
     * '买家微信openid'
     */
    private String buyerOpenid;
    /**
     * '订单总金额'
     */
    private BigDecimal orderAmount;
    /**
     * '订单状态, 默认为新下单'
     */
    private Integer orderStatus;
    /**
     * '支付状态, 默认未支付'
     */
    private Integer payStatus;
    /**
     * '创建时间'
     */
    private Date createTime;
    /**
     * '修改时间'
     */
    private Date updateTime;

}
