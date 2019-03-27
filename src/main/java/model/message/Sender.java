package model.message;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Sender {
    private String queueName;
    private Channel channel;

    public Sender(String queueName) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setRequestedHeartbeat(20);
        factory.setHost("localhost");
        this.queueName = queueName;
        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
            this.channel = channel;
            channel.queueDeclare(queueName, false, false, false, null);
        }
    }

    public boolean sendMessage(String message) throws IOException {
        try{
            channel.basicPublish("", queueName, null, message.getBytes("UTF-8"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
