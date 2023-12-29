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
    /*
     * Méthode gérant la désérialisation de l'ensemble des données, puis leur stockage dans un objet Data
     * 
     * @return data
    */
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

            /* Gestion des réferences entre les objets Equipment et leur EquipmentType */
            for(Equipment equipment : equipments) {
                Integer id_equipmentType = equipment.getId_type();
                for(EquipmentType type : equipmentTypes) {
                    if(type.getId() == id_equipmentType) {
                        equipment.setType(type);
                    }
                }
            }

            /* Gestion des réferences entre les objets Booking et leur Person
             * et les réferences avec leur objet Equipment
             */
            for(Booking booking : bookings) {
                /* Si l'objet Person est du type Admnistrator */
                if(booking.getId_user() == -1) {
                    Integer id_person = booking.getId_administrator();
                    for(Administrator administrator : administrators) {
                        if(administrator.getId() == id_person) {
                            booking.setPerson(administrator);
                        }
                    }
                }
                /* Sinon, l'objet Person est du type User */ 
                else {
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

            /* Sauvegarde des données dans Data */
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