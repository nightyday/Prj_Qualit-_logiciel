package qualite_log.data_import.serializers;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import qualite_log.model.Administrator;
import qualite_log.model.Booking;
import qualite_log.model.Data;
import qualite_log.model.Equipment;
import qualite_log.model.EquipmentType;
import qualite_log.model.User;

public class SerializerTest {
    Data data;
    Administrator admin;
    User user;
    EquipmentType equipT;
    Equipment equip;
    Booking book;



    @Before
    public void initData() {
        data = Data.getInstance();

        admin = new Administrator("michel", "dubois", "michel.dubois@univ-tours.fr");
        data.addAdministrator(admin);
        equipT = new EquipmentType("laptops");
        data.addEquipmentTypes(equipT);
        equip = new Equipment("LAP-176", "Asus Tuf", "QAIJP", equipT);
        user = new User("jhon", "doe", "jhon.doe@etu.univ-tours.fr");
        data.addUsers(user);
        book = new Booking(user, equip, LocalDate.of(2024, 01, 10),  LocalDate.of(2024, 01, 15));
        data.addBookings(book); 
    }

    @Test
    public void EquipmentTypeSerializer() {
        Writer jsonWriter = new StringWriter();
        try {
            JsonGenerator jsonGenerator = new JsonFactory().createGenerator(jsonWriter);
            SerializerProvider serializerProvider = new ObjectMapper().getSerializerProvider();
            new EquipmentTypeSerializer().serialize(equipT, jsonGenerator, serializerProvider);
            jsonGenerator.flush();
    
            System.out.println("Test serialisation EquipmentType :");
            assertEquals(jsonWriter.toString(), "{\"id\":" + equipT.getId() + ",\"type\":\"" + equipT.getType() + "\"}");
            System.out.println("Reussi\n");
        } catch (IOException e) {
            System.err.println("Rate\n");
            e.printStackTrace();
        }
    }

    @Test
    public void EquipmentSerializer() {
        Writer jsonWriter = new StringWriter();
        try {
            JsonGenerator jsonGenerator = new JsonFactory().createGenerator(jsonWriter);
            SerializerProvider serializerProvider = new ObjectMapper().getSerializerProvider();
            new EquipmentSerializer().serialize(equip, jsonGenerator, serializerProvider);
            jsonGenerator.flush();
    
            System.err.println("Test serialisation Equipment");
            assertEquals(jsonWriter.toString(), "{\"reference\":\"" + equip.getReference() + "\",\"name\":\"" + equip.getName() + "\",\"version\":\"" + 
            equip.getVersion() + "\",\"id_type\":" + equipT.getId() + "}");

            System.out.println("Reussi\n");
        } catch (IOException e) {
            System.err.println("Rate\n");
            e.printStackTrace();
        }
    }

    @Test
    public void PersonSerializer() {
        Writer jsonWriterAdministrator = new StringWriter();
        Writer jsonWriterUser = new StringWriter();
        try {
            JsonGenerator jsonGenerator = new JsonFactory().createGenerator(jsonWriterAdministrator);
            SerializerProvider serializerProvider = new ObjectMapper().getSerializerProvider();
            new PersonSerializer().serialize(admin, jsonGenerator, serializerProvider);
            jsonGenerator.flush();
    
            System.err.println("Test serialisation Administrator :");
            assertEquals(jsonWriterAdministrator.toString(), "{\"id\":" + admin.getId() + ",\"lastName\":\"" + admin.getLastName() + "\",\"firstName\":\"" + admin.getFirstName() 
            + "\",\"type\":\"" + admin.getType() + "\",\"email\":\"" + admin.getEmail() + "\"}");

            System.out.println("Reussi\n");
        } catch (IOException e) {
            System.err.println("Rate\n");
            e.printStackTrace();
        }
        try {
            JsonGenerator jsonGenerator = new JsonFactory().createGenerator(jsonWriterUser);
            SerializerProvider serializerProvider = new ObjectMapper().getSerializerProvider();
            new PersonSerializer().serialize(user, jsonGenerator, serializerProvider);
            jsonGenerator.flush();
    
            System.err.println("Test serialisation User :");
            assertEquals(jsonWriterUser.toString(), "{\"id\":" + user.getId() + ",\"lastName\":\"" + user.getLastName() + "\",\"firstName\":\"" + user.getFirstName() 
            + "\",\"type\":\"" + user.getType() + "\",\"email\":\"" + user.getEmail() + "\"}");

            System.out.println("Reussi\n");
        } catch (IOException e) {
            System.err.println("Rate\n");
            e.printStackTrace();
        }
    }
    
    @Test
    public void BookingSerializer() {
        Writer jsonWriter = new StringWriter();
        try {
            JsonGenerator jsonGenerator = new JsonFactory().createGenerator(jsonWriter);
            SerializerProvider serializerProvider = new ObjectMapper().getSerializerProvider();
            new BookingSerializer().serialize(book, jsonGenerator, serializerProvider);
            jsonGenerator.flush();
    
            System.out.println("Test serialisation Booking :");
            assertEquals(jsonWriter.toString(), "{\"id\":" + book.getId() + ",\"id_person\":" + user.getId() + ",\"type_person\":\"" + user.getType() + 
            "\",\"reference_equipment\":\"" + equip.getReference() + "\",\"startingDate\":\"" + book.getStartingDate() + "\",\"endingDate\":\"" + book.getEndingDate() + "\"}");
            
            System.out.println("Reussi\n");
        } catch (IOException e) {
            System.err.println("Rate\n");
            e.printStackTrace();
        }
    }
}
