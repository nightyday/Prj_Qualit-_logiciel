package qualite_log;

import java.time.LocalDate;

import qualite_log.data_import.DataReader;
import qualite_log.data_import.DataWriter;
import qualite_log.model.Administrator;
import qualite_log.model.Booking;
import qualite_log.model.Data;
import qualite_log.model.Equipment;
import qualite_log.model.EquipmentType;
import qualite_log.model.User;

public class Main {
    public static void main(String[] args) {
        Data data = new Data();

        Administrator admin = new Administrator("michel", "dubois", "michel.dubois@univ-tours.fr");
        data.addAdministrator(admin);
        EquipmentType equipT = new EquipmentType();
        data.addEquipmentTypes(equipT);
        Equipment equip = new Equipment(equipT);
        User user = new User("jhon", "doe", "jhon.doe@etu.univ-tours.fr");
        data.addUsers(user);
        data.addBookings(new Booking(user, equip, LocalDate.of(2024, 01, 10),  LocalDate.of(2024, 01, 15))); 
        data.addBookings(new Booking(user, equip, LocalDate.of(2024, 01, 10),  LocalDate.of(2024, 01, 15))); 
        data.addBookings(new Booking(user, equip, LocalDate.of(2024, 01, 10),  LocalDate.of(2024, 01, 15))); 
        data.addBookings(new Booking(user, equip, LocalDate.of(2024, 01, 10),  LocalDate.of(2024, 01, 15))); 
        data.addBookings(new Booking(user, equip, LocalDate.of(2024, 01, 10),  LocalDate.of(2024, 01, 15))); 

        DataWriter.extract(data);
        Data data2 = DataReader.insert();

    }
}
