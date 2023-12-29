package qualite_log.data_import.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import qualite_log.model.Booking;

public class BookingSerializer extends JsonSerializer<Booking> {
     public BookingSerializer() {
        super();
    }

    @Override
    public void serialize(Booking booking, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        jgen.writeNumberField("id", booking.getId());
        jgen.writeNumberField("id_person", booking.getPerson().getId());
        jgen.writeStringField("type_person", booking.getPerson().getType());
        jgen.writeNumberField("id_equipment", booking.getEquipment().getId());
        jgen.writeStringField("startingDate", booking.getStartingDate().toString());
        jgen.writeStringField("endingDate", booking.getEndingDate().toString());
        jgen.writeEndObject();
    }
}