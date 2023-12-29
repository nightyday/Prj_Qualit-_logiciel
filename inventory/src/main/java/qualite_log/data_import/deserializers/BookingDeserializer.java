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

/* Surcharge permettant une désérialisation correcte des données Booking  */
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


        /* Initialisation des champs de Equipment */
        Integer id = node.get("id").asInt();
        LocalDate startingDate = LocalDate.parse(node.get("startingDate").asText());
        LocalDate endingDate = LocalDate.parse(node.get("endingDate").asText());

        int id_person = (Integer) ((IntNode) node.get("id_person")).numberValue();// Id qui permettra de lié à l'objet Person correspondant
        String type_person = node.get("type_person").asText();// Champs qui permettra de déterminer le type de l'objet Person
        int id_equipment = (Integer) ((IntNode) node.get("id_equipment")).numberValue();// Id qui permettra de lié à l'objet Equipment correspondant

        Booking booking;
        /* Création de l'objet Booking */
        if(type_person.equals("administrator")) {
            booking = new Booking(id, id_person, -1, id_equipment); // Si l'objet person lié est un Administrator, l'id_person est enregistré dans le champs id_administrator
        } else {
            booking = new Booking(id, -1, id_person, id_equipment); // Sinon, l'objet person lié est un User, l'id_person est donc enregistré dans le champs id_user        
        }
        
        booking.setStartingDate(startingDate);
        booking.setEndingDate(endingDate);

        return booking;
    }
}
