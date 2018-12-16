package com.zxc.order.order.service.impl;

import com.zxc.order.order.dao.OrderDetailRepository;
import com.zxc.order.order.dao.OrderMasterRespository;
import com.zxc.order.order.domain.dto.OrderDTO;
import com.zxc.order.order.domain.po.OrderMaster;
import com.zxc.order.order.menus.OrderStatus;
import com.zxc.order.order.menus.PayStatus;
import com.zxc.order.order.service.OrderService;
import com.zxc.order.order.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * TODO...
 *
 * @author Zhou RunMing
 * @date 2018/12/16
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRespository orderMasterRespository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        OrderMaster orderMasterCarry = new OrderMaster();
        orderDTO.setOrderId(KeyUtil.genOrdrUniqueKey());
        BeanUtils.copyProperties(orderDTO, orderMasterCarry);
        setOrderMasterInCreateStatus(orderMasterCarry);
        orderMasterCarry.setOrderAmount(new BigDecimal(50));
        orderMasterCarry.setOrderId(KeyUtil.genOrdrUniqueKey());
        this.orderMasterRespository.save(orderMasterCarry);
        return orderDTO;
    }

    public static OrderMaster setOrderMasterInCreateStatus(OrderMaster orderMaster) {
        if (orderMaster == null)
            return null;
        Date now = new Date();
        orderMaster.setCreateTime(now);
        orderMaster.setUpdateTime(now);
        orderMaster.setOrderStatus(OrderStatus.NEW.getCode());
        orderMaster.setPayStatus(PayStatus.WAIT.getCode());
        return orderMaster;
    }
}
