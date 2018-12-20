package com.zxc.order.service;

import com.zxc.order.domain.dto.OrderDTO;

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

    /**
     * 完成订单(改变订单状态,只能卖家操作)
     * @param orderId
     * @return
     */
    OrderDTO finish(String orderId);

}
