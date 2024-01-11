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

    private String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public EquipmentType() {
        setId();
        equipments = new ArrayList<>();
    }

    public EquipmentType(String type) {
        setId();
        this.type = type;
        equipments = new ArrayList<>();
    }

    public String toString() {
        return type;
    }
}