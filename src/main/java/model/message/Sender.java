package model.message;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Sender {
    private Channel channel;
    private final String queueName;

    public Sender(final String queueName) throws IOException, TimeoutException {
        this.queueName = queueName;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setRequestedHeartbeat(20);
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
//            channel.queueDeclare(queueName, false, false, false, null);
            this.channel = channel;
        }
    }

    public void sendMessage(String message) {
        try{
            channel.basicPublish("", queueName, null, message.getBytes("UTF-8"));
            System.out.println(queueName + " Sent '" + message + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
