package qualite_log.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idPerson")
public class Person {
    private static Long nextId = 1L;
    private Long idPerson;
    
    @JsonManagedReference
    private List<Booking> bookings;

    private String lastName;
    private String firstName;
    private String type;
    private String email; 


    public Long NextgetId() {
        return nextId;
    }
    
    public Long getIdPerson() {
        return idPerson;
    }

    private void setIdPerson() {
        idPerson = nextId++;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public void addBookings(Booking booking) {
        bookings.add(booking);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Person() {
        setIdPerson();
        bookings = new ArrayList<>();
    }

    public Person(String lastName, String firstName, String type, String email) {
        setIdPerson();
        this.lastName = lastName;
        this.firstName = firstName;
        this.type = type;
        this.email = email;
        bookings = new ArrayList<>();
    }
    
    public String toString() {
        return lastName + " " + firstName;
    }
}
