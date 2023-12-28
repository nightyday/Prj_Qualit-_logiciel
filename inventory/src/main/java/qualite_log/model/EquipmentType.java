package qualite_log.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class EquipmentType {
    private static Long nextId = 1L;
    private Long id;

    @JsonProperty("equipments")
    @JsonManagedReference
    List<Equipment> equipments;

    String label;
    String reference;

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }

    public void addEquipments(Equipment equipment) {
        equipments.add(equipment);
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public EquipmentType() {
        setId();
        equipments = new ArrayList<>();
        this.reference = "XXX";
    }

    public EquipmentType(String label, String reference) {
        setId();
        this.label = label;
        this.reference = reference;
        equipments = new ArrayList<>();
    }

    public String toString() {
        return reference;
    }
}