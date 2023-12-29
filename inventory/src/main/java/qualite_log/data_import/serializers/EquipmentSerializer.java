package qualite_log.data_import.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import qualite_log.model.Equipment;

public class EquipmentSerializer extends JsonSerializer<Equipment> {
     public EquipmentSerializer() {
        super();
    }

    @Override
    public void serialize(Equipment equipment, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        jgen.writeNumberField("id", equipment.getId());
        jgen.writeStringField("reference", equipment.getReference());
        jgen.writeStringField("version", equipment.getVersion());
        jgen.writeNumberField("id_type", equipment.getType().getId());
        jgen.writeEndObject();
    }
}
