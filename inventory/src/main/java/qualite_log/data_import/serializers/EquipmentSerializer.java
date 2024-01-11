package qualite_log.data_import.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import qualite_log.model.Equipment;

/* Surcharge permettant une sérialisation correcte des données Equipment */
public class EquipmentSerializer extends JsonSerializer<Equipment> {
     public EquipmentSerializer() {
        super();
    }

    @Override
    public void serialize(Equipment equipment, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        jgen.writeStringField("reference", equipment.getReference());
        jgen.writeStringField("nom", equipment.getNom());
        jgen.writeStringField("version", equipment.getVersion());
        jgen.writeNumberField("id_type", equipment.getType().getId()); // Sauvegarde de l'Id de l'EquipmentType
        jgen.writeEndObject();
    }
}
