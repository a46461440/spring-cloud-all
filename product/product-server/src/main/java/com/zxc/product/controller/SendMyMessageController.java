package com.zxc.product.controller;

import com.zxc.product.domain.po.ProductInfo;
import com.zxc.product.message.ReceiveClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Zhou RunMing
 * @Date 2018-12-18
 */
@RestController
public class SendMyMessageController {

    @Autowired
    private ReceiveClient receiveClient;

    @PostMapping("/sendMsg")
    public void sendToMyMessage(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123456");
        productInfo.setCreateTime(new Date());
        this.receiveClient.message1Output().send(MessageBuilder.withPayload(productInfo).build());
    }

}
