package forms.firestation.gateway;

import gateways.MessageSenderGateway;
import model.Serializer.FireStationSerializer;
import model.firestation.FireStationReply;
import model.firestation.FireStationRequest;
import utilities.Constants;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class FireStationSenderGateway {
    private MessageSenderGateway senderGateway;
    private FireStationSerializer fireStationSerializer;

    public FireStationSenderGateway() {
        try {
            this.fireStationSerializer = new FireStationSerializer();
            this.senderGateway = new MessageSenderGateway(Constants.FIRE_STATION_REPLY);
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(FireStationReply reply) {
        senderGateway.sendMessage(fireStationSerializer.FireStationReplyToString(reply), reply.getAlarmId());
        System.out.println("Reply sent! " + reply.toString());
    }
}
