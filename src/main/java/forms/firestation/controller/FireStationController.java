package forms.firestation.controller;

import forms.firestation.gateway.FireStationReceiverGateway;
import forms.firestation.gateway.FireStationSenderGateway;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class FireStationController {
    @FXML
    private ListView lvFireStation;

    private FireStationReceiverGateway receiver;
    private FireStationSenderGateway sender;

    public FireStationController() {
        this.receiver = new FireStationReceiverGateway();
        this.sender = new FireStationSenderGateway();
    }

    public void sendReply() {
        System.out.println("WE'RE COMING!");
    }
}
