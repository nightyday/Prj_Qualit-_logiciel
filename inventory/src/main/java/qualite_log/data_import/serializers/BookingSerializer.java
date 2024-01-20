package qualite_log.data_import.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import qualite_log.model.Booking;

/* Surcharge permettant une sérialisation correcte des données Booking */
public class BookingSerializer extends JsonSerializer<Booking> {
    public BookingSerializer() {
        super();
    }

    @Override
    public void serialize(Booking booking, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        jgen.writeNumberField("id", booking.getId());
        jgen.writeNumberField("id_person", booking.getEmprunter().getId()); // Sauvegarde de l'Id de l'emprunteur
        jgen.writeStringField("type_person", booking.getEmprunter().getType()); // Sauvegarde du type de Person correspondant à l'emprunteur
        jgen.writeStringField("reference_equipment", booking.getEquipment().getReference()); // Sauvegarde de l'Id de l'Equipment
        jgen.writeStringField("startingDate", booking.getStartingDate().toString());
        jgen.writeStringField("endingDate", booking.getEndingDate().toString());
        jgen.writeEndObject();
    }
}