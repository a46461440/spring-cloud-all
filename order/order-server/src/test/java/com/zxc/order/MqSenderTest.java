package com.zxc.order;

import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 发送mq消息测试
 *
 * @author Zhou RunMing
 * @Date 2018-12-18
 */
@Component
public class MqSenderTest extends OrderApplicationTests {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void sendMeg() {
        this.amqpTemplate.convertAndSend("myQueue", "now:" + new Date());
    }

    @Test
    public void sendOrder() {
        this.amqpTemplate.convertAndSend("myOrder", "computer","now:" + new Date());
    }

}
