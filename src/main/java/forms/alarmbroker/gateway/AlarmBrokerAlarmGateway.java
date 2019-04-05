package forms.alarmbroker.gateway;

import com.rabbitmq.client.DeliverCallback;
import gateways.MessageReceiverGateway;
import gateways.MessageSenderGateway;
import model.Serializer.AlarmSerializer;
import model.alarm.AlarmReply;
import model.alarm.AlarmRequest;
import utilities.Constants;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class AlarmBrokerAlarmGateway {
    private MessageSenderGateway senderGateway;
    private MessageReceiverGateway receiverGateway;
    private AlarmSerializer serializer;

    public AlarmBrokerAlarmGateway() {
        try {
            this.serializer = new AlarmSerializer();
            this.receiverGateway = new MessageReceiverGateway(Constants.ALARM_REQUEST, createNewDeliverCallback());
            this.senderGateway = new MessageSenderGateway(Constants.ALARM_REPLY);
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    private DeliverCallback createNewDeliverCallback(){
        return (s, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            onRequestArrived(serializer.stringToAlarmRequest(message));
            System.out.println("Alarm Request received '" + message + "'");
        };
    }

    public void sendMessage(int id, AlarmReply reply) {
        senderGateway.sendMessage(serializer.AlarmReplyToString(reply), id);
    }

    public void onRequestArrived(AlarmRequest request) {

    }
}
