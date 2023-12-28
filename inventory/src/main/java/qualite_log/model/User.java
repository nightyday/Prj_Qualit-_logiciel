package qualite_log.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User extends Person {
    public User() {
        super();
    }

    public User(String lastName, String firstName, String email) {
        super(lastName, firstName, false, email);
    }
}
