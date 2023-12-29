package qualite_log.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import qualite_log.data_import.serializers.EquipmentTypeSerializer;

@JsonSerialize(using = EquipmentTypeSerializer.class)
public class EquipmentType {
    private static Integer nextId = 1;
    private Integer id;

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

    public Integer NextgetId() {
        return nextId;
    }

    public Integer getId() {
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