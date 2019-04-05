package forms.alarm.gateway;

import com.rabbitmq.client.DeliverCallback;
import gateways.MessageReceiverGateway;
import model.Serializer.AlarmSerializer;
import model.alarm.AlarmReply;
import utilities.Constants;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class AlarmReceiverGateway {
    private MessageReceiverGateway receiverGateway;
    private AlarmSerializer serializer;

    public AlarmReceiverGateway() {
        try {
            this.serializer = new AlarmSerializer();
            this.receiverGateway = new MessageReceiverGateway(Constants.ALARM_REPLY, createNewDeliverCallback());
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    private DeliverCallback createNewDeliverCallback(){
        return (s, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            onReplyArrived(serializer.stringToAlarmReply(message));
            System.out.println("Alarm Reply received '" + message + "'");
        };
    }

    public void onReplyArrived(AlarmReply reply) {
        //Method for UI updates to override
    }
}
