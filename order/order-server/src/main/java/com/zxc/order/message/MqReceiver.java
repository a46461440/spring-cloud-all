package com.zxc.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Zhou RunMing
 * @Date 2018-12-18
 */
@Slf4j
@Component
public class MqReceiver {

    //  1.需要手动创建队列  @RabbitListener(queues = {"myQueue"})
//  2.自动创建队列方式 @RabbitListener(queuesToDeclare = {@Queue("myQueue")})
//  3.自动创建队列并且和Exchange绑定
    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue("myQueue"), exchange = @Exchange("myOrder"))
    })
    public void process(String message) {
        this.log.info("mqReceive:{}", message);
    }

    /**
     * 数码供应商服务 接受消息
     * @param message
     */
    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue("computerOrder"), exchange = @Exchange("myOrder"), key = "computer")
    })
    public void processComputer(String message) {
        this.log.info("computer mqReceive:{}", message);
    }

    /**
     * 水果供应商服务 接受消息
     * @param message
     */
    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue("fruitOrder"), exchange = @Exchange("myOrder"), key = "fruit")
    })
    public void processFruit(String message) {
        this.log.info("computer mqReceive:{}", message);
    }

}
