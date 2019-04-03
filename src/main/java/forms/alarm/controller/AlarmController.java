package forms.alarm.controller;

import forms.alarm.gateway.AlarmReceiverGateway;
import forms.alarm.gateway.AlarmSenderGateway;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.alarm.AlarmReply;
import model.alarm.AlarmRequest;

import java.util.Date;
import java.util.Random;

public class AlarmController {
    @FXML
    private ListView lvAlarmRequests;

    private int id = 0;
    private AlarmReceiverGateway receiver;
    private AlarmSenderGateway sender;
    private String[] testLocations;
    private Random rnd;

    public AlarmController() {
        receiver = new AlarmReceiverGateway(){
            @Override
            public void onReplyArrived(AlarmReply reply) {
                super.onReplyArrived(reply);
                sender.messageReceived(reply.getAlarmId());
            }
        };
        sender = new AlarmSenderGateway();
        rnd = new Random();
        rnd.setSeed(new Date().getTime());
        testLocations = new String[5];
        testLocations[0] = "Venray";
        testLocations[1] = "Amsterdam";
        testLocations[2] = "Venlo";
        testLocations[3] = "Blerick";
        testLocations[4] = "Eindhoven";
    }

    public void sendMessage() {
        sender.sendMessage(new AlarmRequest(id++, testLocations[rnd.nextInt(testLocations.length)]));
    }

    public void test() {
        sender.messageReceived(2);
    }
}
