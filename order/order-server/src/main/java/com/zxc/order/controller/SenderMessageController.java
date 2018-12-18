package com.zxc.order.controller;

import com.zxc.order.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Zhou RunMing
 * @Date 2018-12-18
 */
@RestController
@RequestMapping("/stream")
public class SenderMessageController {

    @Autowired
    private StreamClient streamClient;

    @GetMapping("/sendMessage")
    public void process() {
        String message = "now:" + new Date();
        this.streamClient.message2Output().send(MessageBuilder.withPayload(message).build());
    }

}
