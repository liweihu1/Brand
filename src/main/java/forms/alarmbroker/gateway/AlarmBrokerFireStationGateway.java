package forms.alarmbroker.gateway;

import com.rabbitmq.client.DeliverCallback;
import gateways.MessageReceiverGateway;
import gateways.MessageSenderGateway;
import model.Serializer.FireStationSerializer;
import model.firestation.FireStationReply;
import model.firestation.FireStationRequest;
import utilities.Constants;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class AlarmBrokerFireStationGateway {

    private MessageSenderGateway senderGateway;
    private MessageReceiverGateway receiverGateway;
    private FireStationSerializer serializer;

    public AlarmBrokerFireStationGateway() {
        try {
            this.serializer = new FireStationSerializer();
            this.receiverGateway = new MessageReceiverGateway(Constants.FIRE_STATION_REPLY, createNewDeliverCallback());
            this.senderGateway = new MessageSenderGateway(Constants.FIRE_STATION_REQUEST);
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }


    private DeliverCallback createNewDeliverCallback(){
        return (s, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            onReplyArrived(serializer.stringToFireStationReply(message));
            System.out.println("FireStation Reply received '" + message + "'");
        };
    }

    public void sendMessage(FireStationRequest request) {
        senderGateway.sendMessage(serializer.FireStationRequestToString(request), request.getAlarmId());
        System.out.println("Calculating closest fire station...");
        System.out.println("Request sent!");
    }

    public void onReplyArrived(FireStationReply reply) {

    }
}
