package qualite_log.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import qualite_log.data_import.deserializers.EquipmentDeserializer;
import qualite_log.data_import.serializers.EquipmentSerializer;

@JsonSerialize(using = EquipmentSerializer.class)
@JsonDeserialize(using = EquipmentDeserializer.class)
public class Equipment {
    private String reference;

    private EquipmentType type;

    private String nom;
    private String version;

    public EquipmentType getType() {
        return type;
    }

    public void setType(EquipmentType type) {
        this.type.getEquipments().remove(this);
        this.type = type;
        type.addEquipments(this);
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    public Equipment(String reference, EquipmentType type) {
        this.reference = reference;

        this.type = type;
        
        type.addEquipments(this);
    }

    public Equipment(String reference, String nom, String version, EquipmentType type) {
        this.reference = reference;

        this.type = type;

        this.nom = nom;
        this.version = version;

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
    public Equipment(String reference, Integer id_type) {
        this.reference = reference;

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
