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
import qualite_log.model.Person;
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
            String currentDirectory = System.getProperty("user.dir");
            String path = currentDirectory + "/inventory/data/";

            ObjectMapper mapper = new ObjectMapper();

            data = insertEquipmentTypes(path, mapper, data);
            data = insertUsers(path, mapper, data);
            data = insertAdministrators(path, mapper, data);

            insertEquipments(path, mapper, data);
            data = insertBookings(path, mapper, data);

            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * Gère la mise à jour des objets User dans data
     * 
     * @param path
     * @param mapper
     * @param data
     * 
     * @return data
     */
    private static Data insertUsers(String path, ObjectMapper mapper, Data data) throws Exception {
        data.setUsers(Arrays.asList(mapper.readValue(new File(path + "users.json"), User[].class)));

        return data;
    }

    /*
     * Gère la mise à jour des objets Administrator dans data
     * 
     * @param path
     * @param mapper
     * @param data
     * 
     * @return data
     */
    private static Data insertAdministrators(String path, ObjectMapper mapper, Data data) throws Exception {
        data.setAdministrators(Arrays.asList(mapper.readValue(new File(path + "administrators.json"), Administrator[].class)));

        return data;
    }

    /*
     * Gère la mise à jour des objets Equipment dans data
     * 
     * @param path
     * @param mapper
     * @param data
     * 
     * @return equipments
     */
    private static List<Equipment> insertEquipments(String path, ObjectMapper mapper, Data data) throws Exception {
        List<Equipment> equipments = Arrays.asList(mapper.readValue(new File(path + "equipments.json"), Equipment[].class));
        managedEquipmentsReferences(equipments, data.getEquipmentTypes()); // On gère les réferences des objets Equipment

        return equipments;
    }

    /*
     * Gère la mise à jour des objets EquipmentType dans data
     * 
     * @param path
     * @param mapper
     * @param data
     * 
     * @return data
     */
    private static Data insertEquipmentTypes(String path, ObjectMapper mapper, Data data) throws Exception {
        data.setEquipmentTypes(Arrays.asList(mapper.readValue(new File(path + "equipment_types.json"), EquipmentType[].class)));

        return data;
    }

    /*
     * Gère la mise à jour des objets Booking dans data
     * 
     * @param path
     * @param mapper
     * @param data
     * 
     * @return data
     */
    private static Data insertBookings(String path, ObjectMapper mapper, Data data) throws Exception {
        List<Booking> bookings = Arrays.asList(mapper.readValue(new File(path + "bookings.json"), Booking[].class));

        managedBookingsReferences(bookings, data.getAdministrators(), data.getUsers(), data.getEquipments()); // On gère les réferences
        data.setBookings(bookings);

        return data;
    }

    /* Gestion des réferences entre les objets Equipment et leur EquipmentType 
     * 
     * @param equipments
     * @param equipmentTypes
    */
    private static void managedEquipmentsReferences(List<Equipment> equipments, List<EquipmentType> equipmentTypes) throws Exception {   
        try {
            for (Equipment equipment : equipments) {
                EquipmentType type_toSet = null;

                Integer id_equipmentType = equipment.getId_type();
                for (EquipmentType type : equipmentTypes) {
                    if (type.getId() == id_equipmentType) {
                        type_toSet = type;
                    }
                }

                /* Si aucun EquipmentType n'est trouvé */
                if (type_toSet == null) {
                    throw new NullPointerException("L'id du type d'equipement defini dans l'equipement d'id " + equipment.getId() 
                    + " ne correspond à aucune EquipmentType existant.");
                }

                equipment.defineReferences(type_toSet);
            }
        } catch(NullPointerException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        
    }

    /* Gestion des réferences entre les objets Booking et leur Person et les réferences avec leur objet Equipment 
     * 
     * @param bookings
     * @param administrators
     * @param users
     * @param equipments
    */
    private static void managedBookingsReferences(List<Booking> bookings, List<Administrator> administrators, List<User> users, List<Equipment> equipments) throws Exception {
        
        try {
            for (Booking booking : bookings) {
                Person emprunter_toSet = null;
                Equipment equipment_toSet = null;

                /* Si l'objet Person est du type Admnistrator */
                if (booking.getId_user() == -1) {
                    Integer id_person = booking.getId_administrator();
                    for (Administrator administrator : administrators) {
                        if (administrator.getId() == id_person) {
                            emprunter_toSet = administrator;
                        }
                    }
                } 
                /* Sinon, l'objet Person est du type User */ 
                else {
                    Integer id_person = booking.getId_user();
                    for (User user : users) {
                        if (user.getId() == id_person) {
                            emprunter_toSet = user;
                        }
                    }
                }

                Integer id_equipment = booking.getId_equipment();
                for (Equipment equipment : equipments) {
                    if (equipment.getId() == id_equipment) {
                        equipment_toSet = equipment;
                    }
                }

                /* Si aucune Person n'est trouvé */
                if (emprunter_toSet == null) {
                    throw new NullPointerException("L'id de l'emprunteur defini dans la réservation d'id " + booking.getId() 
                    + " ne correspond à aucune Person existante.");
                }
                /* Si aucun Equipment n'est trouvé */
                if (equipment_toSet == null) {
                    throw new NullPointerException("L'id de l'equipement defini dans la réservation d'id " + booking.getId() 
                    + " ne correspond à aucune Equipment existant.");
                }

                booking.defineReferences(emprunter_toSet, equipment_toSet);
            }
        } catch(NullPointerException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}