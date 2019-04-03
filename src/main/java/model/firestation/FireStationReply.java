package model.firestation;

import java.io.Serializable;

public class FireStationReply implements Serializable {
    private String message;

    public FireStationReply() {
        message = "";
    }

    public  FireStationReply(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
