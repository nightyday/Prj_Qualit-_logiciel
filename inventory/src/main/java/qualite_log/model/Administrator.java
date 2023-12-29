package qualite_log.model;

//@JsonSerialize(using = PersonSerializer.class)
public class Administrator extends Person {
    public Administrator() {
        super();
    }

    public Administrator(String lastName, String firstName, String email) {
        super(lastName, firstName, "administrator", email);
    }
}
