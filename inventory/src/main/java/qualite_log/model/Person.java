package qualite_log.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idPerson")
public class Person {
    private static Long nextId = 1L;
    private Long idPerson;
    

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
    }

    public Person(String lastName, String firstName, String type, String email) {
        setIdPerson();
        this.lastName = lastName;
        this.firstName = firstName;
        this.type = type;
        this.email = email;
    }
    
    public String toString() {
        return lastName + " " + firstName;
    }
}
