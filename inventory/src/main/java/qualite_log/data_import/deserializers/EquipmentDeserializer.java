package qualite_log.data_import.deserializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;

import qualite_log.model.Equipment;

/* Surcharge permettant une désérialisation correcte des données Equipment */
public class EquipmentDeserializer extends StdDeserializer<Equipment> {
    
    public EquipmentDeserializer() {
        this(null); 
    }

    public EquipmentDeserializer(Class<Equipment> e) { 
        super(e); 
    }

    public Equipment deserialize(JsonParser jp, DeserializationContext ctxt) 
  throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);

        /* Initialisation des champs de Equipment */
        Integer id = node.get("id").asInt();
        String reference = node.get("reference").asText();
        String version = node.get("version").asText();

        int id_type = (Integer) ((IntNode) node.get("id_type")).numberValue(); // Id qui permettra de lié à l'objet EquipmentType correspondant

        /* Création de l'objet Equipment */
        Equipment equipment = new Equipment(id, id_type);
        equipment.setReference(reference);
        equipment.setVersion(version);

        return equipment;
    }
}
