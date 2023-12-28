package qualite_log.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Equipment {
    private static Long nextId = 1L;
    private Long id;

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

    public Long NextgetId() {
        return nextId;
    }

    public Long getId() {
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
}
