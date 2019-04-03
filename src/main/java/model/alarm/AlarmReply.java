package model.alarm;

import java.io.Serializable;

public class AlarmReply implements Serializable {
    private int alarmId;
    private String message;

    public AlarmReply() {
        alarmId = 0;
        message = "";
    }

    public AlarmReply(int id, String message) {
        this.alarmId = id;
        this.message = message;
    }

    public int getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(int alarmId) {
        this.alarmId = alarmId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
