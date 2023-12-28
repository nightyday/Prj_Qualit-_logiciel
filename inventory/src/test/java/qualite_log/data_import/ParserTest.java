package qualite_log.data_import;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import qualite_log.model.Administrator;
import qualite_log.model.Booking;
import qualite_log.model.Data;
import qualite_log.model.Equipment;
import qualite_log.model.EquipmentType;
import qualite_log.model.User;

@RunWith(Suite.class)
@SuiteClasses({ DataReader.class, DataWriter.class })
public class ParserTest {
    @Test
    public void test1() {
        Data data = new Data();

        Administrator admin = new Administrator("michel", "dubois", "michel.dubois@univ-tours.fr");
        data.addAdministrator(admin);
        EquipmentType equipT = new EquipmentType();
        data.addEquipmentTypes(equipT);
        Equipment equip = new Equipment(equipT);
        data.addUsers(new User("jhon", "doe", "jhon.doe@etu.univ-tours.fr"));
        data.addBookings(new Booking(admin, equip)); 

        print(data);

        DataWriter.extract(data);
        Data data2 = DataReader.insert();

        print(data2);
    }

    public static void print(Data data) {
        for(Administrator a : data.getAdministrators()) {
            System.out.println(a);
        }
        for(User u : data.getUsers()) {
            System.out.println(u);
        }
        for(Equipment e : data.getEquipments()) {
            System.out.println(e);
        }
        for(EquipmentType e : data.getEquipmentTypes()) {
            System.out.println(e);
        }
        for(Booking  b : data.getBookings()) {
            System.out.println(b);
        }
    }
}
