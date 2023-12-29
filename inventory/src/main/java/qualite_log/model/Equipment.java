package qualite_log.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import qualite_log.data_import.deserializers.EquipmentDeserializer;
import qualite_log.data_import.serializers.EquipmentSerializer;

@JsonSerialize(using = EquipmentSerializer.class)
@JsonDeserialize(using = EquipmentDeserializer.class)
public class Equipment {
    private static Integer nextId = 1;
    private Integer id;

    private EquipmentType type;

    private String reference;
    private String version;

    public EquipmentType getType() {
        return type;
    }

    public void setType(EquipmentType type) {
        this.type = type;

        type.addEquipments(this);
    }

    public Integer NextgetId() {
        return nextId;
    }

    public Integer getId() {
        return id;
    }

    private void setId() {
        id = nextId++;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Equipment(Integer id, Integer id_type) {
        this.id = id;
        nextId = id + 1;

        this.id_type = id_type;
    }

    public Equipment() {
        setId();
        reference = "XXXXX";
    }

    public Equipment(EquipmentType type) {
        setId();
        reference = "XXXXX";
        setType(type);
    }

    public Equipment(String reference, String version) {
        setId();
        this.reference = reference;
        this.version = version;
    }

    public Equipment(String reference, String version, EquipmentType type) {
        setId();
        this.reference = reference;
        this.version = version;
        setType(type);
    }
    
    public String toString() {
        return type.toString() + "-" + reference;
    }

    int id_type = -1;

    public int getId_type() {
        return id_type;
    }    
}
