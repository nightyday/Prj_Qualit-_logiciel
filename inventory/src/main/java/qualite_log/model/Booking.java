package qualite_log.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Booking {
    @JsonProperty("person")
    @JsonManagedReference
    Person person;

    @JsonProperty("equipment")
    @JsonManagedReference
    Equipment equipment;

    LocalDate startingDate;
    LocalDate endingDate;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
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

    public Booking(Person person, Equipment equipment) {
        this.person = person;
        this.equipment = equipment;
    }

    public Booking(Person person, Equipment equipment, LocalDate startingDate, LocalDate endingDate) {
        this.person = person;
        this.equipment = equipment;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }

    public String toString() {
        return person.toString() + " : " + equipment.toString();
    }
}
