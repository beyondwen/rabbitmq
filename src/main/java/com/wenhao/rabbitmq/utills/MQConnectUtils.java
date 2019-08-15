package com.wenhao.rabbitmq.utills;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MQConnectUtils {

    public static Connection newConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("11.0.0.123");
        factory.setUsername("wenhao");
        factory.setPassword("wenhao151");
        factory.setPort(5672);
        factory.setVirtualHost("/wenhao");
        return factory.newConnection();
    }
}
