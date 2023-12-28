package qualite_log.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idEquipmentType")
public class EquipmentType {
    private static Long nextId = 1L;
    private Long idEquipmentType;

    @JsonManagedReference
    private List<Equipment> equipments;

    private String label;
    private String reference;

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

    public Long getIdEquipmentType() {
        return idEquipmentType;
    }

    private void setIdEquipmentType() {
        idEquipmentType = nextId++;
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
        setIdEquipmentType();
        equipments = new ArrayList<>();
        this.reference = "XXX";
    }

    public EquipmentType(String label, String reference) {
        setIdEquipmentType();
        this.label = label;
        this.reference = reference;
        equipments = new ArrayList<>();
    }

    public String toString() {
        return reference;
    }
}