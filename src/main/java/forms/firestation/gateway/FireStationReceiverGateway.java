package forms.firestation.gateway;

import com.rabbitmq.client.DeliverCallback;
import gateways.MessageReceiverGateway;
import model.Serializer.FireStationSerializer;
import model.firestation.FireStationRequest;
import utilities.Constants;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class FireStationReceiverGateway {
    private MessageReceiverGateway receiverGateway;
    private FireStationSerializer serializer;

    public FireStationReceiverGateway() {
        try {
            this.serializer = new FireStationSerializer();
            this.receiverGateway = new MessageReceiverGateway(Constants.FIRE_STATION_REQUEST, createNewDeliverCallback());
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    private DeliverCallback createNewDeliverCallback(){
        return (s, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            onRequestArrived(serializer.stringToFireStationRequest(message));
            System.out.println("Fire Station Request received '" + message + "'");
        };
    }

    public void onRequestArrived(FireStationRequest reply) {
        //TODO something
    }
}
