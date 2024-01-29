package qualite_log.model;

import java.util.Objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import qualite_log.data_import.deserializers.EquipmentDeserializer;
import qualite_log.data_import.serializers.EquipmentSerializer;

@JsonSerialize(using = EquipmentSerializer.class) // Balise pour spécifié la serialisation personnalisée à utiliser
@JsonDeserialize(using = EquipmentDeserializer.class) // Balise pour spécifié la déserialisation personnalisée à utiliser
public class Equipment {
    private String reference;

    private EquipmentType type;

    private String name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Constructeur à ne pas utiliser
     */
    private Equipment() {
    }

    public Equipment(String reference, EquipmentType type) {
        this.reference = reference;

        this.type = type;

        type.addEquipments(this);
    }

    public Equipment(String reference, String name, String version, EquipmentType type) {
        this.reference = reference;

        this.type = type;

        this.name = name;
        this.version = version;

        type.addEquipments(this);
    }

    public String toString() {
        return type.toString() + "-" + reference;
    }

    ///// OUTILS A LA DESERIALISATION
    ///// ////////////////////////////////////////////////////////////////////////////////////////////////////

    /* Attributs et méthodes utiles à la déserialisation */
    int idType = -1;

    /*
     * Constructeur spécific à la désérialisation, ne peut utiliser autre part
     * (risque d'incohérence des ids)
     * 
     * @param id
     * 
     * @param id_type
     */
    public Equipment(String reference, Integer idType) {
        this.reference = reference;

        this.idType = idType;
    }

    public int getIdType() {
        return idType;
    }

    /*
     * Méthode servant à la définition de type dans le cas d'une création par le
     * constructeur de désérialisation
     * 
     * @param emprunter
     * 
     * @param equipment
     */
    public void defineReferences(EquipmentType type) {
        this.type = type;
        type.addEquipments(this);
    }

    // Surcharge de la méthode equals pour pouvoir comparer deux objets de type
    // Equipment

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Equipment that = (Equipment) obj;
        return Objects.equals(getReference(), that.getReference());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReference());
    }

}
