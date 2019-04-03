package model.message;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeoutException;

public class TimeoutSender {
    private Channel channel;
    private final String queueName;
    private List<Integer> toSentMessages;
    private List<Integer> deadMessages;
    private Timer timer;
    private ConnectionFactory factory;

    public TimeoutSender(final String queueName) throws IOException, TimeoutException {
        this.timer = new Timer();
        this.deadMessages = new ArrayList<>();
        this.toSentMessages = new ArrayList<>();
        this.queueName = queueName;
        factory = new ConnectionFactory();
        factory.setRequestedHeartbeat(20);
        factory.setHost("localhost");
        System.out.println("Sender started");
    }

    public void sendMessage(String message, int id) {
        try{
            try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
                channel.queueDeclare(queueName, false, false, false, null);
                this.channel = channel;
                channel.basicPublish("", queueName, null, message.getBytes("UTF-8"));
                toSentMessages.add(id);
                startTimer();
                System.out.println(queueName + " Sent '" + message + "'");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startTimer() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                deadMessages.addAll(toSentMessages);
                timer.cancel();
                System.out.println(deadMessages.size());
            }
        }, 30000);
    }

    public List<Integer> getDeadMessages() {
        List<Integer> deadMessages = new ArrayList<>(this.deadMessages);
        this.deadMessages = new ArrayList<>();
        return deadMessages;
    }

    public void messageWasReceived(Integer id) {
        this.toSentMessages.remove(id);
    }
}
