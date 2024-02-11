package qualite_log.model;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import qualite_log.data_import.deserializers.BookingDeserializer;
import qualite_log.data_import.serializers.BookingSerializer;

@JsonSerialize(using = BookingSerializer.class) // Balise pour spécifié la serialisation personnalisée à utiliser
@JsonDeserialize(using = BookingDeserializer.class) // Balise pour spécifié la déserialisation personnalisée à utiliser
public class Booking {
    private static Integer nextId = 1;
    private Integer id;

    Person emprunter;

    Equipment equipment;

    LocalDate startingDate;
    LocalDate endingDate;

    public Person getEmprunter() {
        return emprunter;
    }

    public StringProperty getEmail() {
        StringProperty email = new SimpleStringProperty();
        email.setValue(emprunter.getEmail());
        return email;
    }

    /*
     * Accesseur en écriture de l'attribut emprunter, gère aussi les changements sur l'emprunteur
     * 
     * @param emprunter
     */
    public void setEmprunter(Person emprunter) {
        this.emprunter.getBookings().remove(this);
        this.emprunter = emprunter;
        emprunter.addBookings(this);
    }

    public Equipment getEquipment() {
        return equipment;
    }

    /*
     * Accesseur en écriture de l'attribut reference de sorte à obtenir un StringProperty, utile aux vues
     * 
     * @param reference
     */
    public StringProperty getReference() {
        StringProperty referenceEquipmentString = new SimpleStringProperty();
        referenceEquipmentString.setValue(equipment.getReference());
        return referenceEquipmentString;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Integer getId() {
        return id;
    }

    private static void nextId() {
        nextId ++;
    }

    private void setId() {
        id = nextId;
        nextId();
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public LocalDate getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(LocalDate endingDate) {
        this.endingDate = endingDate;
    }

    /**
     * Constructeur à ne pas utiliser
     */
    @SuppressWarnings("unused")
    private Booking() {}

    public Booking(Person emprunter, Equipment equipment) {
        setId();
        this.emprunter = emprunter;
        emprunter.addBookings(this);
        this.equipment = equipment;
        this.startingDate = LocalDate.now();
        this.endingDate = LocalDate.now().plusMonths(2);
    }

    public Booking(Person emprunter, Equipment equipment, LocalDate startingDate, LocalDate endingDate) {
        setId();
        this.emprunter = emprunter;
        emprunter.addBookings(this);
        this.equipment = equipment;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }

    public String toString() {
        return emprunter.toString() + " : " + equipment.toString();
    }

    ///// OUTILS A LA DESERIALISATION ////////////////////////////////////////////////////////////////////////////////////////////////////

    /* Attributs et méthodes utiles à la déserialisation */
    private int idAdministrator = -1;
    private int idUser = -1;
    private String referenceEquipment = "XXX-XXX";

    /* 
     * Constructeur spécific à la désérialisation, ne peut utiliser autre part (risque d'incohérence des ids) 
     * 
     * @param id
     * @param idAdministrator
     * @param idUser
     * @param reference
    */
    public Booking(Integer id, Integer idAdministrator, Integer idUser, String referenceEquipment) {
        this.id = id;

        this.idAdministrator = idAdministrator;
        this.idUser = idUser;
        this.referenceEquipment = referenceEquipment;
    }

    public int getIdAdministrator() {
        return idAdministrator;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getReferenceEquipment() {
        return referenceEquipment;
    }

    /*
     * Méthode servant à la définition de emprunter et equipment dans le cas d'une création par le constructeur de désérialisation
     * 
     * @param emprunter
     * @param equipment
     */
    public void defineReferences(Person emprunter, Equipment equipement) {
        this.emprunter = emprunter;
        emprunter.addBookings(this);
        this.equipment = equipement;
    }
}
