package com.wenhao.rabbitmq.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.wenhao.rabbitmq.utills.MQConnectUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class Producer {

    private static final String QUEUE_NAME = "myqueue";

    @Scheduled(fixedDelay = 5000)
    public static void send() throws IOException, TimeoutException {
        Connection connection = MQConnectUtils.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String msg = "创建消息";
        System.out.println("投递消息了");
        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        channel.close();
        connection.close();
    }
}
