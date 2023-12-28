package qualite_log.data_import;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import qualite_log.model.Administrator;
import qualite_log.model.Booking;
import qualite_log.model.Data;
import qualite_log.model.Equipment;
import qualite_log.model.EquipmentType;
import qualite_log.model.User;

public class DataReader {
    public static Data insert() {
        Data data = new Data();

        try {
            String path = "/home/arthur/Documents/Polytech-Tours/S7/Qualité Logiciel/Prj_Qualit-_logiciel/inventory/data/";

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());

            List<EquipmentType> equipmentTypes = Arrays
                    .asList(mapper.readValue(new File(path + "equipment_types.json"), EquipmentType[].class));
            System.out.println("et init" );

            List<Equipment> equipments = Arrays
                    .asList(mapper.readValue(new File(path + "equipments.json"), Equipment[].class));
            System.out.println("equip init");
            
            List<Booking> bookings = Arrays.asList(mapper.readValue(new File(path + "bookings.json"), Booking[].class));
            System.out.println("book init");

            List<User> users = Arrays.asList(mapper.readValue(new File(path + "users.json"), User[].class));
            System.out.println("user init");
            List<Administrator> administrators = Arrays
                    .asList(mapper.readValue(new File(path + "administrators.json"), Administrator[].class));
            System.out.println("admin init");

            data.setEquipmentTypes(equipmentTypes);
            data.setBookings(bookings);
            data.setUsers(users);
            data.setAdministrators(administrators);

            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
};