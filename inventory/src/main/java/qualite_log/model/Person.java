package qualite_log.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import qualite_log.data_import.serializers.PersonSerializer;

@JsonSerialize(using = PersonSerializer.class)
public class Person {
    private static Integer nextId = 1;
    private Integer id;
    
    private List<Booking> bookings;

    private String lastName;
    private String firstName;
    private String type;
    private String email;

    public Integer getNextId() {
        return nextId;
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
        setId();
        bookings = new ArrayList<>();
    }

    public Person(String lastName, String firstName, String type, String email) {
        setId();
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
