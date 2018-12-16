package com.zxc.order.order.service;

import com.zxc.order.order.domain.dto.OrderDTO;

import java.util.List;

/**
 * TODO...
 *
 * @author Zhou RunMing
 * @date 2018/12/16
 */
public interface OrderService {
    /**
     * 创建订单
     * @param orderDTO 其中包括了OrderMaster和{@link List}OrderDetail
     * @return
     */
    OrderDTO create(OrderDTO orderDTO);

}
