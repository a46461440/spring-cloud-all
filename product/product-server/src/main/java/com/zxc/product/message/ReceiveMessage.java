package com.zxc.product.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * @author Zhou RunMing
 * @Date 2018-12-18
 */
@Component
@Slf4j
@EnableBinding(ReceiveClient.class)
public class ReceiveMessage {

    @StreamListener(ReceiveClient.MESSAGE2)
    public void readMessage(String msg) {
        this.log.info("product module receive:{}", msg);
    }

}
