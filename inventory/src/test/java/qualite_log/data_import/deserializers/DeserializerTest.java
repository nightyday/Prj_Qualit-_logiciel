package qualite_log.data_import.deserializers;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;

import qualite_log.model.Booking;
import qualite_log.model.Data;
import qualite_log.model.Equipment;
import qualite_log.model.EquipmentType;

public class DeserializerTest {
    ObjectMapper mapper;
    List<EquipmentType> typesByEquipmentsId;

    @Before
    public void setup() {
        mapper = new ObjectMapper();
    }

    @Test
    public void EquipmentTypeDeserializer() {
        EquipmentDeserializer deserializer = new EquipmentDeserializer();
    
        try {
            String json = "{ \"reference\" : \"AN001\", \"name\" : \"A32\", \"version\" : \"1.2\", \"id_type\" : 1 }";
            JsonParser jsonParser = mapper.getFactory().createParser(json);
            DeserializationContext ctxt = mapper.getDeserializationContext();

            Equipment equipment = deserializer.deserialize(jsonParser, ctxt);

            assertEquals("AN001", equipment.getReference());
            assertEquals("A32", equipment.getName());
            assertEquals("1.2", equipment.getVersion());
            assertEquals(1, equipment.getIdType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void BookingDeserializer() {
        BookingDeserializer deserializer = new BookingDeserializer();
    
        try {
            String json = "{\r\n" + //
                    "  \"id\" : 2,\r\n" + //
                    "  \"id_person\" : 3,\r\n" + //
                    "  \"type_person\" : \"user\",\r\n" + //
                    "  \"reference_equipment\" : \"AN001\",\r\n" + //
                    "  \"startingDate\" : \"2023-12-09\",\r\n" + //
                    "  \"endingDate\" : \"2024-01-09\"\r\n" + //
                    "}";
            JsonParser jsonParser = mapper.getFactory().createParser(json);
            DeserializationContext ctxt = mapper.getDeserializationContext();

            Booking booking = deserializer.deserialize(jsonParser, ctxt);

            assertEquals(-1, booking.getIdAdministrator());
            assertEquals(3, booking.getIdUser());
            assertEquals("AN001", booking.getReferenceEquipment());
            assertEquals(LocalDate.of(2023, 12, 9), booking.getStartingDate());
            assertEquals(LocalDate.of(2024, 1, 9), booking.getEndingDate());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}