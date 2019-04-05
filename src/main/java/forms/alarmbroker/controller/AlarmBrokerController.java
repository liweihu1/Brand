package forms.alarmbroker.controller;

import forms.alarmbroker.gateway.AlarmBrokerAlarmGateway;
import forms.alarmbroker.gateway.AlarmBrokerFireStationGateway;
import model.alarm.AlarmReply;
import model.alarm.AlarmRequest;
import model.firestation.FireStationReply;
import model.firestation.FireStationRequest;

public class AlarmBrokerController {
    private AlarmBrokerAlarmGateway alarmBrokerAlarmGateway;
    private AlarmBrokerFireStationGateway alarmBrokerFireStationGateway;

    public AlarmBrokerController() {
        this.alarmBrokerAlarmGateway = new AlarmBrokerAlarmGateway() {
            @Override
            public void onRequestArrived(AlarmRequest request) {
                super.onRequestArrived(request);
                alarmBrokerFireStationGateway.sendMessage(new FireStationRequest(request.getId(), request.getLocation()));
            }
        };
        this.alarmBrokerFireStationGateway = new AlarmBrokerFireStationGateway(){
            @Override
            public void onReplyArrived(FireStationReply reply) {
                super.onReplyArrived(reply);
                alarmBrokerAlarmGateway.sendMessage(reply.getAlarmId(), new AlarmReply(reply.getAlarmId(), reply.getMessage()));
            }
        };
    }
}
