package qualite_log.model;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import qualite_log.data_import.deserializers.BookingDeserializer;
import qualite_log.data_import.serializers.BookingSerializer;

@JsonSerialize(using = BookingSerializer.class)
@JsonDeserialize(using = BookingDeserializer.class)
public class Booking {
    public static Integer nextId = 1;
    private Integer id;

    Person emprunter;

    Equipment equipment;

    LocalDate startingDate;
    LocalDate endingDate;

    public Person getEmprunter() {
        return emprunter;
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

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
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

    /* 
     * Constructeur à ne pas utiliser
     * 
     * @throws NullPointerException
     */
    public Booking() throws NullPointerException {
        throw new NullPointerException("Erreur : Une réservation doit crée en spécifiant un emprunteur et un equipement");
    }

    public Booking(Person emprunter, Equipment equipment) {
        setId();
        this.emprunter = emprunter;
        emprunter.addBookings(this);
        this.equipment = equipment;
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
    int id_administrator = -1;
    int id_user = -1;
    String reference = "XXX-XXX";

    /* 
     * Constructeur spécific à la désérialisation, ne peut utiliser autre part (risque d'incohérence des ids) 
     * 
     * @param id
     * @param id_administrator
     * @param id_user
     * @param is_equipment
    */
    public Booking(Integer id, Integer id_administrator, Integer id_user, String reference) {
        this.id = id;

        this.id_administrator = id_administrator;
        this.id_user = id_user;
        this.reference = reference;
    }

    public int getId_administrator() {
        return id_administrator;
    }

    public int getId_user() {
        return id_user;
    }

    public String getReference_equipment() {
        return reference;
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
