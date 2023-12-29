package qualite_log.data_import.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import qualite_log.model.EquipmentType;

/* Surcharge permettant une sérialisation correcte des données EquipmentType */
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

        // On ne s'occupe pas des objets Equipment liés, il seront géré dans la sérialisation des objets Equipment (Circular references)
        
        jgen.writeEndObject();
    }
}
