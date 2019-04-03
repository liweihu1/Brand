package forms.firestation.gateway;

import gateways.MessageSenderGateway;
import model.Serializer.FireStationSerializer;
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
            senderGateway = new MessageSenderGateway(Constants.FIRE_STATION_REPLY);
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(FireStationRequest request) {
        senderGateway.sendMessage(fireStationSerializer.FireStationRequestToString(request), request.getAlarmId());
    }
}
