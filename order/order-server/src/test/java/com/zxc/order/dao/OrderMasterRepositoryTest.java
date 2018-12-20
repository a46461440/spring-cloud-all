package com.zxc.order.dao;

import com.zxc.order.OrderApplicationTests;
import com.zxc.order.domain.po.OrderMaster;
import com.zxc.order.menus.OrderStatus;
import com.zxc.order.menus.PayStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
@Slf4j
public class OrderMasterRepositoryTest extends OrderApplicationTests {

    @Autowired
    private OrderMasterRepository OrderMasterRepository;

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
        OrderMaster result = this.OrderMasterRepository.save(orderMaster);
        Assert.assertTrue(result != null);
        System.out.printf(result.toString());
    }

    @Test
    public void testUpdateStatus() {
        Integer affectRows = this.OrderMasterRepository.updateChangeStatus("1544940702006764669",
                OrderStatus.NEW.getCode(), OrderStatus.FINISHED.getCode());
        this.log.info(affectRows.toString());
    }
}