package qualite_log.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties("bookings")
public class Person {
    private static Long nextId = 1L;
    private Long id;
    
    @JsonBackReference
    List<Booking> bookings;

    String lastName;
    String firstName;
    boolean isAdministrator;
    String email; 

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public void addBookings(Booking booking) {
        bookings.add(booking);
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

    public boolean isAdministrator() {
        return isAdministrator;
    }

    public void setAdministrator(boolean isAdministrator) {
        this.isAdministrator = isAdministrator;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Person() {
        setId();
        bookings = new ArrayList<>();
    }

    public Person(String lastName, String firstName, boolean isAdministrator, String email) {
        setId();
        this.lastName = lastName;
        this.firstName = firstName;
        this.isAdministrator = isAdministrator;
        this.email = email;
        bookings = new ArrayList<>();
    }
    
    public String toString() {
        return lastName + " " + firstName;
    }
}
