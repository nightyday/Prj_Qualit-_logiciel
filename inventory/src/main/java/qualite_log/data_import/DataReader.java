package qualite_log.data_import;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

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

            List<EquipmentType> equipmentTypes = Arrays
                    .asList(mapper.readValue(new File(path + "equipment_types.json"), EquipmentType[].class));

            List<User> users = Arrays.asList(mapper.readValue(new File(path + "users.json"), User[].class));

            List<Administrator> administrators = Arrays
                    .asList(mapper.readValue(new File(path + "administrators.json"), Administrator[].class));            

            List<Equipment> equipments = Arrays
                    .asList(mapper.readValue(new File(path + "equipments.json"), Equipment[].class));

            List<Booking> bookings = Arrays.asList(mapper.readValue(new File(path + "bookings.json"), Booking[].class));

            for(Equipment equipment : equipments) {
                Integer id_equipmentType = equipment.getId_type();
                for(EquipmentType type : equipmentTypes) {
                    if(type.getId() == id_equipmentType) {
                        equipment.setType(type);
                    }
                }
            }

            for(Booking booking : bookings) {
                if(booking.getId_user() == -1) {
                    Integer id_person = booking.getId_administrator();
                    for(Administrator administrator : administrators) {
                        if(administrator.getId() == id_person) {
                            booking.setPerson(administrator);
                        }
                    }
                } else {
                    Integer id_person = booking.getId_user();
                    for(User user : users) {
                        if(user.getId() == id_person) {
                            booking.setPerson(user);
                        }
                    }
                }

                Integer id_equipment = booking.getId_equipment();
                for(Equipment equipment : equipments) {
                    if(equipment.getId() == id_equipment) {
                        booking.setEquipment(equipment);
                    }
                }
            }

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