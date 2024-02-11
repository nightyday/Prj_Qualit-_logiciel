package qualite_log.model;

import java.util.List;

public class User extends Person {
    public User() {
        super();
    }

    public User(String lastName, String firstName, String email) {
        super(lastName, firstName, "user", email);
    }
}
