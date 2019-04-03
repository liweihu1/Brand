package model.firestation;

import java.io.Serializable;

public class FireStationRequest implements Serializable {
    private int alarmId;
    private String location;

    public FireStationRequest() {
        alarmId = -1;
        location = "";
    }

    public FireStationRequest(int id, String location) {
        this.alarmId = id;
        this.location = location;
    }

    public int getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(int alarmId) {
        this.alarmId = alarmId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
