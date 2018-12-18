package com.zxc.product.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author Zhou RunMing
 * @Date 2018-12-18
 */
public interface ReceiveClient {

    String MESSAGE1 = "myMessage";

    String MESSAGE2 = "messageAfterMyMessage";

    @Output(ReceiveClient.MESSAGE1)
    MessageChannel message1Output();

    @Input(ReceiveClient.MESSAGE2)
    SubscribableChannel message2Input();

}
