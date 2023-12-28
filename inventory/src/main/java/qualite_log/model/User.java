package qualite_log.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idPerson")
public class User extends Person {
    

    public User() {
        super();
    }

    public User(String lastName, String firstName, String email) {
        super(lastName, firstName, "user", email);
    }
}
