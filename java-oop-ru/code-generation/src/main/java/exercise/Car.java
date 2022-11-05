package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.io.IOException;

// BEGIN
@AllArgsConstructor
@Getter
@Value
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    public String serialize() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        // mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.writeValueAsString(this);
    }

    public static Car unserialize(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Car.class);
    }
    // END
}
