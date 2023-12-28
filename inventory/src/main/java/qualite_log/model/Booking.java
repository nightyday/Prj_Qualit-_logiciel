package qualite_log.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties({"person", "equipment"})
public class Booking {
    private static Long nextId = 1L;
    private Long id;

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

    public Long NextgetId() {
        return nextId;
    }

    public Long getId() {
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

    public Booking(Person person, Equipment equipment) {
        setId();
        this.person = person;
        this.equipment = equipment;
    }

    public Booking(Person person, Equipment equipment, LocalDate startingDate, LocalDate endingDate) {
        setId();
        this.person = person;
        this.equipment = equipment;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }

    public String toString() {
        return person.toString() + " : " + equipment.toString();
    }
}
