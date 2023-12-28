package qualite_log.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Equipment {
    @JsonProperty("type")
    @JsonBackReference
    EquipmentType type;

    String reference;
    String version;

    public EquipmentType getType() {
        return type;
    }

    public void setType(EquipmentType type) {
        this.type = type;

        type.addEquipments(this);
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

    public Equipment() {
        reference = "XXXXX";
    }

    public Equipment(EquipmentType type) {
        reference = "XXXXX";
        setType(type);
    }

    public Equipment(String reference, String version) {
        this.reference = reference;
        this.version = version;
    }

    public Equipment(String reference, String version, EquipmentType type) {
        this.reference = reference;
        this.version = version;
        setType(type);
    }
    
    public String toString() {
        return type.toString() + "-" + reference;
    }
}
