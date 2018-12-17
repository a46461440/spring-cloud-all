package com.zxc.order.domain.dto;

import com.zxc.order.domain.po.OrderDetail;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * OrderMaster表和OrderDetail表的实体集成在一起
 *
 * @author Zhou RunMing
 * @date 2018/12/16
 */
@Data
public class OrderDTO implements Serializable{

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

    private List<OrderDetail> orderDetailList;

}
