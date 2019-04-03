package model.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.alarm.AlarmReply;
import model.alarm.AlarmRequest;

import java.io.IOException;

public class AlarmSerializer {
    private ObjectMapper mapper;

    public AlarmSerializer() {
        this.mapper = new ObjectMapper();
    }

    public String AlarmRequestToString(AlarmRequest request) {
        try {
            return this.mapper.writeValueAsString(request);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String AlarmReplyToString(AlarmReply reply) {
        try {
            return this.mapper.writeValueAsString(reply);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public AlarmRequest stringToAlarmRequest(String response) {
        try {
            return this.mapper.readValue(response, AlarmRequest.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public AlarmReply stringToAlarmReply(String response) {
        try {
            return this.mapper.readValue(response, AlarmReply.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
