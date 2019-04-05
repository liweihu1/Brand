package forms.firestation.controller;

import forms.firestation.gateway.FireStationReceiverGateway;
import forms.firestation.gateway.FireStationSenderGateway;
import model.firestation.FireStationReply;
import model.firestation.FireStationRequest;

import java.util.Random;

public class FireStationController{
    private FireStationReceiverGateway receiver;
    private FireStationSenderGateway sender;
    private Random rnd;

    public FireStationController() {
        rnd = new Random();
        this.receiver = new FireStationReceiverGateway() {
            @Override
            public void onRequestArrived(FireStationRequest request) {
                super.onRequestArrived(request);
                sendReply(request);
            }
        };
        this.sender = new FireStationSenderGateway();
    }

    public void sendReply(FireStationRequest req) {
        if (rnd.nextInt(10) > 2) {
            FireStationReply reply = new FireStationReply("WE'RE COMING!!!!!", req.getAlarmId());
            sender.sendMessage(reply);
        }
    }
}
