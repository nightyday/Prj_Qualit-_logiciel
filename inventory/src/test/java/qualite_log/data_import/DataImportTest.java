package qualite_log.data_import;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import qualite_log.model.Administrator;
import qualite_log.model.Booking;
import qualite_log.model.Data;
import qualite_log.model.Equipment;
import qualite_log.model.EquipmentType;
import qualite_log.model.User;

/*
 * Les tests n'ont pas l'air de fonctionner, j'ai l'impression que les read and write ne fonctionnent pas dans des méthodes de tests
 */
public class DataImportTest {
    Data dataPreImport;
    Data dataPostImport;

    @BeforeClass
    public static void beforeClass() {
        System.out.println("\n===================================\n===Début tests data_import");
    }    

    @Before
    public void before() {
        dataPreImport = new Data();

        Administrator admin = new Administrator("michel", "dubois", "michel.dubois@univ-tours.fr");
        dataPreImport.addAdministrator(admin);
        EquipmentType equipT = new EquipmentType("laptops", "LAP");
        dataPreImport.addEquipmentTypes(equipT);
        Equipment equip = new Equipment("QAIJP", "Asus Tuf - QAIJP", equipT);
        User user = new User("jhon", "doe", "jhon.doe@etu.univ-tours.fr");
        dataPreImport.addUsers(user);
        dataPreImport.addBookings(new Booking(user, equip, LocalDate.of(2024, 01, 10),  LocalDate.of(2024, 01, 15))); 

        DataWriter.extract(dataPreImport);
    }

    @Test
    public void correspondanceWriteAndRead() {
        dataPostImport = DataReader.insert();

        assertEquals(dataPreImport.getAdministrators(), dataPostImport.getAdministrators());
        assertEquals(dataPreImport.getUsers(), dataPostImport.getUsers());
        assertEquals(dataPreImport.getEquipmentTypes(), dataPostImport.getEquipmentTypes());
        assertEquals(dataPreImport.getEquipments(), dataPostImport.getEquipments());
        assertEquals(dataPreImport.getBookings(), dataPostImport.getBookings());
    }

    @Test
    public void updateAdministrator() {
        Administrator admin1 = new Administrator("michel", "dubois", "michel.dubois@univ-tours.fr");
        Administrator admin2 = new Administrator("bob", "alice", "bob.alice@univ-tours.fr");

        dataPreImport.setAdministrators(Arrays.asList(admin1, admin2));

        DataWriter.extractAdministrators(dataPreImport);
        dataPreImport = dataPostImport;

        dataPreImport = DataReader.insertAdministrators(dataPreImport);
    }
}
