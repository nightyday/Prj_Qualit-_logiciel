package qualite_log.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import qualite_log.data_import.serializers.PersonSerializer;

//@JsonSerialize(using = PersonSerializer.class)
public class User extends Person {
    public User() {
        super();
    }

    public User(String lastName, String firstName, String email) {
        super(lastName, firstName, "user", email);
    }
}
