package model.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.firestation.FireStationReply;
import model.firestation.FireStationRequest;

import java.io.IOException;

public class FireStationSerializer {
    private ObjectMapper mapper;

    public FireStationSerializer() {
        this.mapper = new ObjectMapper();
    }

    public String FireStationRequestToString(FireStationRequest request) {
        try {
            return this.mapper.writeValueAsString(request);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String FireStationReplyToString(FireStationReply reply) {
        try {
            return this.mapper.writeValueAsString(reply);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public FireStationRequest stringToFireStationRequest(String response) {
        try {
            return this.mapper.readValue(response, FireStationRequest.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public FireStationReply stringToFireStationReply(String response) {
        try {
            return this.mapper.readValue(response, FireStationReply.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
