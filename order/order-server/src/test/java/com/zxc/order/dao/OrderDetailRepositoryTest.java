package com.zxc.order.dao;

import com.zxc.order.OrderApplicationTests;
import com.zxc.order.domain.po.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
public class OrderDetailRepositoryTest extends OrderApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void testSaveOrderDetail() {
        OrderDetail orderDetailCarry = new OrderDetail();
        orderDetailCarry.setDetailId("123123123213");
        orderDetailCarry.setOrderId("123456");
        orderDetailCarry.setProductIcon("http://xxx.com");
        orderDetailCarry.setProductName("皮蛋瘦肉粥");
        orderDetailCarry.setProductId("157875196366160023");
        orderDetailCarry.setProductPrice(new BigDecimal(20));
        orderDetailCarry.setProductQuantity(2);
        Date now = new Date();
        orderDetailCarry.setCreateTime(now);
        orderDetailCarry.setUpdateTime(now);
        OrderDetail result = this.orderDetailRepository.save(orderDetailCarry);
        Assert.assertTrue(result != null);
        System.out.printf(result.toString());
    }

}