package com.zxc.order.order.dao;

import com.zxc.order.order.OrderApplicationTests;
import com.zxc.order.order.domain.po.OrderMaster;
import com.zxc.order.order.menus.OrderStatus;
import com.zxc.order.order.menus.PayStatus;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

@Component
public class OrderMasterRespositoryTest extends OrderApplicationTests{

    @Autowired
    private OrderMasterRespository orderMasterRespository;

    @Test
    public void testSave() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123456");
        orderMaster.setBuyerName("周xm");
        orderMaster.setBuyerPhone("16670150527");
        orderMaster.setBuyerAddress("深圳");
        orderMaster.setOrderAmount(new BigDecimal(25));
        orderMaster.setBuyerOpenid("132213213");
        orderMaster.setOrderStatus(OrderStatus.NEW.getCode());
        orderMaster.setPayStatus(PayStatus.WAIT.getCode());
        Date now = new Date();
        orderMaster.setCreateTime(now);
        orderMaster.setUpdateTime(now);
        OrderMaster result = this.orderMasterRespository.save(orderMaster);
        Assert.assertTrue(result != null);
        System.out.printf(result.toString());
    }
}