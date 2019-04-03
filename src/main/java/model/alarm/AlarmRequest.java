package model.alarm;

import java.io.Serializable;

public class AlarmRequest implements Serializable {
    private int id;
    private String location;

    public AlarmRequest() {
        this.id = 0;
        this.location = "";
    }

    public AlarmRequest(int id, String location) {
        this.id = id;
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
