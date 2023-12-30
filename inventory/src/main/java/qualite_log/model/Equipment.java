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
        this.type.getEquipments().remove(this);
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

    /* 
     * Constructeur à ne pas utiliser
     * 
     * @throws NullPointerException
     */
    public Equipment() throws NullPointerException{
        throw new NullPointerException("Erreur : Un équipement doit être crée en spécifiant un type d'équipement");
    }

    public Equipment(EquipmentType type) {
        setId();
        reference = "XXXXX";
        this.type = type;
        type.addEquipments(this);
    }

    public Equipment(String reference, String version, EquipmentType type) {
        setId();
        this.reference = reference;
        this.version = version;
        this.type = type;
        type.addEquipments(this);
    }
    
    public String toString() {
        return type.toString() + "-" + reference;
    }

    ///// OUTILS A LA DESERIALISATION ////////////////////////////////////////////////////////////////////////////////////////////////////

    /* Attributs et méthodes utiles à la déserialisation */
    int id_type = -1;

    /* 
     * Constructeur spécific à la désérialisation, ne peut utiliser autre part (risque d'incohérence des ids) 
     * 
     * @param id
     * @param id_type
    */
    public Equipment(Integer id, Integer id_type) {
        this.id = id;

        this.id_type = id_type;
    }

    public int getId_type() {
        return id_type;
    }    

    /*
     * Méthode servant à la définition de type dans le cas d'une création par le constructeur de désérialisation
     * 
     * @param emprunter
     * @param equipment
     */
    public void defineReferences(EquipmentType type) {
        this.type = type;
        type.addEquipments(this);
    }
}
