package gateways;

import model.message.TimeoutSender;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class MessageSenderGateway {
    private TimeoutSender sender;

    public MessageSenderGateway(String queueName) throws IOException, TimeoutException {
        this.sender = new TimeoutSender(queueName);
    }

    public void sendMessage(String message, int messageId) {
        this.sender.sendMessage(message, messageId);
    }

    public List<Integer> checkForDeadLetters(){
        return this.sender.getDeadMessages();
    }

    public void messageReceived(Integer id) {
        sender.messageWasReceived(id);
    }
}
