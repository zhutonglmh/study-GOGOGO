package com.zt.study.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * 消息生产者
 */
@Service("producer")
public class Producer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate; // 也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装

    // 发送消息，destination是发送到的队列，message是待发送的消息
     public void sendMessage(Destination destination, final String message){
         jmsMessagingTemplate.convertAndSend(destination, message);
 }
}
