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
        EquipmentType equipT = new EquipmentType("laptop", "LAP");
        data.addEquipmentTypes(equipT);
        Equipment equip = new Equipment("Q68PH", "ASUS-17THK", equipT);
        data.addUsers(new User("jhon", "doe", "jhon.doe@etu.univ-tours.fr"));
        data.addBookings(new Booking(admin, equip, LocalDate.of(2024, 01, 10),  LocalDate.of(2024, 01, 15))); 

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
