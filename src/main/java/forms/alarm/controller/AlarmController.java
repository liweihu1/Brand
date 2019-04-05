package forms.alarm.controller;

import forms.alarm.gateway.AlarmReceiverGateway;
import forms.alarm.gateway.AlarmSenderGateway;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.RequestReply;
import model.alarm.AlarmReply;
import model.alarm.AlarmRequest;

import java.util.*;

public class AlarmController {
    private List<RequestReply<AlarmRequest, AlarmReply>> lvAlarmRequests;
    private int id = 0;
    private Timer timer;
    private AlarmReceiverGateway receiver;
    private AlarmSenderGateway sender;
    private String[] testLocations;
    private Random rnd;

    public AlarmController() {
        this.lvAlarmRequests = new ArrayList<>();
        receiver = new AlarmReceiverGateway(){
            @Override
            public void onReplyArrived(AlarmReply reply) {
                super.onReplyArrived(reply);
                System.out.println("WE'RE COMING FOR YOU!" + reply.getAlarmId());
                sender.messageReceived(reply.getAlarmId());
                addReplyToRequest(reply);
            }
        };
        timer = new Timer();
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
        int messageId = id++;
        AlarmRequest request = new AlarmRequest(messageId, testLocations[rnd.nextInt(testLocations.length)]);
        sender.sendMessage(request);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                RequestReply result = checkListviewForRequest(messageId);
                if (result == null) {
                    System.out.println(messageId + " SOMETHING WENT WRONG CALL 112/911/YOUR LOCAL POLICE NUMBER");
                }
            }
        }, 30000);
        System.out.println("We've send the message!");
    }

    public void addReplyToRequest(AlarmReply reply) {
        checkListviewForRequest(reply.getAlarmId()).setReply(reply);
        System.out.println(checkListviewForRequest(reply.getAlarmId()).toString());
    }

    public RequestReply checkListviewForRequest(int id) {
        RequestReply rr = lvAlarmRequests.stream().filter(f -> f.getRequest().getId() == id).findFirst().orElse(null);
        if (rr != null){
            return rr;
        }
        return null;
    }
}
