package qualite_log.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idBooking")
public class Booking {
    private static Long nextId = 1L;
    private Long idBooking;

    @JsonBackReference
    User emprunter;

    Equipment equipment;

    LocalDate startingDate;
    LocalDate endingDate;

    public User getEmprunter() {
        return emprunter;
    }

    public void setEmprunter(User emprunter) {
        this.emprunter = emprunter;
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

    public Long getIdBooking() {
        return idBooking;
    }

    private void setIdBooking() {
        idBooking = nextId++;
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
        setIdBooking();
    }

    public Booking(User emprunter, Equipment equipment) {
        setIdBooking();
        this.emprunter = emprunter;
        this.equipment = equipment;
    }

    public Booking(User emprunter, Equipment equipment, LocalDate startingDate, LocalDate endingDate) {
        setIdBooking();
        this.emprunter = emprunter;
        this.equipment = equipment;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }

    public String toString() {
        return emprunter.toString() + " : " + equipment.toString();
    }
}
