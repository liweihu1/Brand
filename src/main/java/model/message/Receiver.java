package model.message;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Receiver {

    public Receiver(String queueName, DeliverCallback callback) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        System.out.println(" [*] Waiting for messages on queue " + queueName);
        channel.basicConsume(queueName, true, callback, consumerTag -> { });
    }
}
