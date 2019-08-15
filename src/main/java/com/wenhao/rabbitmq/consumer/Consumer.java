package com.wenhao.rabbitmq.consumer;

import com.rabbitmq.client.*;
import com.wenhao.rabbitmq.utills.MQConnectUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class Consumer {

    private static final String QUEUE_NAME = "myqueue";

    @Scheduled(fixedDelay = 3000)
    public static void rec() throws IOException, TimeoutException {
        Connection connection = MQConnectUtils.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "UTF-8");
                System.out.println(msg);
            }
        };
        channel.basicConsume(QUEUE_NAME, true, defaultConsumer);
        /*channel.close();
        connection.close();*/
    }
}
