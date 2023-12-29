package qualite_log.data_import.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import qualite_log.model.EquipmentType;

public class EquipmentTypeSerializer extends JsonSerializer<EquipmentType> {
     public EquipmentTypeSerializer() {
        super();
    }

    @Override
    public void serialize(EquipmentType equipmentType, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        jgen.writeNumberField("id", equipmentType.getId());
        jgen.writeStringField("label", equipmentType.getLabel());
        jgen.writeStringField("reference", equipmentType.getReference());
        jgen.writeEndObject();
    }
}
