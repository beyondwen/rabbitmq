package com.wenhao.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.wenhao.rabbitmq.utills.MQConnectUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class Consumer {

    private static final String QUEUE_NAME = "myqueue";

    @Scheduled(fixedDelay = 5000)
    public static void send() throws IOException, TimeoutException {
        Connection connection = MQConnectUtils.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
            
        };
        channel.close();
        connection.close();
    }
}
