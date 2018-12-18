package com.zxc.order.message;

import com.zxc.product.domain.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @author Zhou RunMing
 * @Date 2018-12-18
 */
@Component
@Slf4j
@EnableBinding(StreamClient.class)
public class StreamReceiver {

//    @StreamListener(StreamClient.OUTPUT)
//    public void process(Object message) {
//        this.log.info("StreamReceiver:{}" + message);
//    }

    /**
     * 接受来自Product模块的DTO
     * 发送消息给另一队列
     * @param productInfo
     */
    @StreamListener(StreamClient.MESSAGE1)
    @SendTo(StreamClient.MESSAGE2)
    public String process(ProductInfo productInfo) {
        this.log.info("StreamReceiver:{}" + productInfo.toString());
        return "Order module have receive product msg";
    }

}
