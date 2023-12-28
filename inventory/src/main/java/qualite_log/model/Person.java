package qualite_log.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class Person {
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
        bookings = new ArrayList<>();
    }

    public Person(String lastName, String firstName, boolean isAdministrator, String email) {
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
