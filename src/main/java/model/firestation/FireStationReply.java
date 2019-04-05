package model.firestation;

import java.io.Serializable;

public class FireStationReply implements Serializable {
    private String message;
    private int alarmId;

    public FireStationReply() {
        message = "";
        alarmId = 0;
    }

    public  FireStationReply(String message, int id) {
        this.message = message;
        this.alarmId = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(int alarmId) {
        this.alarmId = alarmId;
    }

    @Override
    public String toString() {
        return this.alarmId + " " + this.message;
    }
}
