package qualite_log.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Administrator extends Person {
    public Administrator() {
        super();
    }

    public Administrator(String lastName, String firstName, String email) {
        super(lastName, firstName, true, email);
    }
}
