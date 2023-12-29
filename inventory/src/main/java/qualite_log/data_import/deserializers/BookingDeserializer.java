package qualite_log.data_import.deserializers;

import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;

import qualite_log.model.Booking;

public class BookingDeserializer extends StdDeserializer<Booking> {

    public BookingDeserializer() {
        this(null);
    }
    
    public BookingDeserializer(Class<Booking> b) { 
        super(b); 
    }

    @Override
    public Booking deserialize(JsonParser jp, DeserializationContext ctxt) 
  throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);

        Integer id = node.get("id").asInt();
        LocalDate startingDate = LocalDate.parse(node.get("startingDate").asText());
        LocalDate endingDate = LocalDate.parse(node.get("endingDate").asText());

        int id_person = (Integer) ((IntNode) node.get("id_person")).numberValue();
        String type_person = node.get("type_person").asText();
        int id_equipment = (Integer) ((IntNode) node.get("id_equipment")).numberValue();

        Booking booking;
        if(type_person.equals("administrator")) {
            booking = new Booking(id, id_person, -1, id_equipment);
        } else {
            booking = new Booking(id, -1, id_person, id_equipment);            
        }
        
        booking.setStartingDate(startingDate);
        booking.setEndingDate(endingDate);

        return booking;
    }
}
