package gateways;

import com.rabbitmq.client.DeliverCallback;
import model.message.Receiver;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MessageReceiverGateway {
    private Receiver receiver;

    public MessageReceiverGateway(String queueName, DeliverCallback callback) throws IOException, TimeoutException {
        this.receiver = new Receiver(queueName, callback);

    }
}
