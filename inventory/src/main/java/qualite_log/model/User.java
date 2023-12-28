package qualite_log.model;

public class User extends Person {
    public User(String lastName, String firstName, String email) {
        super(lastName, firstName, false, email);
    }
}
