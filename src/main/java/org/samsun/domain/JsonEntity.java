package org.samsun.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

public abstract class JsonEntity implements Serializable {

    private static final long serialVersionUID = -3039703787284784290L;

    public String toString(){
        ObjectMapper om = new ObjectMapper();
        try {
            return om.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static <T> T valueOf(String jsonValue, Class<T> clazz){

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(jsonValue, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
