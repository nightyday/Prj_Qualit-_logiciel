package qualite_log.model;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import qualite_log.data_import.deserializers.BookingDeserializer;
import qualite_log.data_import.serializers.BookingSerializer;

@JsonSerialize(using = BookingSerializer.class)
@JsonDeserialize(using = BookingDeserializer.class)
public class Booking {
    private static Integer nextId = 1;
    private Integer id;

    Person person;

    Equipment equipment;

    LocalDate startingDate;
    LocalDate endingDate;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
        person.addBookings(this);
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

    public Booking() {
        setId();
    }

    public Booking(Integer id, Integer id_administrator, Integer id_user, Integer id_equipment) {
        this.id = id;
        nextId = id + 1;

        this.id_administrator = id_administrator;
        this.id_user = id_user;
        this.id_equipment = id_equipment;
    }

    public Booking(Person person, Equipment equipment) {
        setId();
        setPerson(person);
        this.equipment = equipment;
    }

    public Booking(Person person, Equipment equipment, LocalDate startingDate, LocalDate endingDate) {
        setId();
        setPerson(person);
        this.equipment = equipment;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }

    public String toString() {
        return person.toString() + " : " + equipment.toString();
    }

    int id_administrator = -1;
    int id_user = -1;
    int id_equipment = -1;

    public int getId_administrator() {
        return id_administrator;
    }

    public int getId_user() {
        return id_user;
    }

    public int getId_equipment() {
        return id_equipment;
    }
}
