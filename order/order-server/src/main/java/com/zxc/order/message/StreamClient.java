package com.zxc.order.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author Zhou RunMing
 * @Date 2018-12-18
 */
public interface StreamClient {

    String MESSAGE1 = "myMessage";
    String MESSAGE2 = "messageAfterMyMessage";

    @Input(StreamClient.MESSAGE1)
    MessageChannel message1Input();

    @Output(StreamClient.MESSAGE2)
    MessageChannel message2Output();

}
