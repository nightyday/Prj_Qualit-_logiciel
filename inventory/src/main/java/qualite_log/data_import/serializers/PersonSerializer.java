package qualite_log.data_import.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import qualite_log.model.Person;

/* Surcharge permettant une sérialisation correcte des données Booking */
public class PersonSerializer extends JsonSerializer<Person> {
    public PersonSerializer() {
        super();
    }

    @Override
    public void serialize(Person person, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        jgen.writeNumberField("id", person.getId());
        jgen.writeStringField("lastName", person.getLastName());
        jgen.writeStringField("firstName", person.getFirstName());
        jgen.writeStringField("type", person.getType());
        jgen.writeStringField("email", person.getEmail());

        // On ne s'occupe pas des objets Booking liés, il seront géré dans la sérialisation des objets Booking (Circular references)

        jgen.writeEndObject();
    }
}
