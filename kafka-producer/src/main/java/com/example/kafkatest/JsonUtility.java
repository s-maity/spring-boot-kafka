package com.example.kafkatest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtility {

    public static String toJsonString(BookingPayload payload) {

        ObjectMapper Obj = new ObjectMapper();

        try {
            return Obj.writeValueAsString(payload);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error in Converting object to jason");
        }
    }

}
