package forms.alarm.gateway;

import gateways.MessageSenderGateway;
import model.Serializer.AlarmSerializer;
import model.alarm.AlarmRequest;
import utilities.Constants;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class AlarmSenderGateway {
    private MessageSenderGateway senderGateway;
    private AlarmSerializer alarmSerializer;

    public AlarmSenderGateway() {
        try {
            this.alarmSerializer = new AlarmSerializer();
            senderGateway = new MessageSenderGateway(Constants.ALARM_REQUEST);
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(AlarmRequest request) {
        senderGateway.sendMessage(alarmSerializer.AlarmRequestToString(request), request.getId());
    }

    public void messageReceived(Integer id) {
        senderGateway.messageReceived(id);
    }
}
