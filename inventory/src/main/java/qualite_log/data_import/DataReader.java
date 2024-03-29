package qualite_log.data_import;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import qualite_log.model.Administrator;
import qualite_log.model.Booking;
import qualite_log.model.Data;
import qualite_log.model.Equipment;
import qualite_log.model.EquipmentType;
import qualite_log.model.Person;
import qualite_log.model.User;
import qualite_log.tool.Encryption;

    

public class DataReader {
    /*
     * Constructeur pour résoudre la recommendation suivante : 
     * 
     * Add a private constructor to hide the implicit public one.
     * 
    */
    private DataReader() {}

    /*
     * Méthode permettant de récupérer le chemin vers un fichier json du dossier data
     * 
     * @param fileName
     * @return le chemin vers fileName
     */
    public static String getPath(String fileName) {
        String currentDirectory = System.getProperty("user.dir");

        return currentDirectory + "/inventory/data/" + fileName;
    }
    
    /*
     * Méthode gérant la désérialisation de l'ensemble des données, puis leur stockage dans un objet Data
     * 
     * @return data
    */
    public static Data insert(Data data) {
        data = insertEquipmentTypes(data);
        data = insertUsers(data);
        data = insertAdministrators(data);

        insertEquipments(data);
        data = insertBookings(data);

        return data;   
    }

    /*
     * Gère la mise à jour des objets User dans data
     * 
     * @param data
     * @return data
     */
    private static Data insertUsers(Data data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            User[] usersArray = mapper.readValue(new File(getPath("users.json")), User[].class);
            List<User> modifiableList = new ArrayList<>(Arrays.asList(usersArray));
            data.setUsers(modifiableList);
        } catch (StreamReadException e) {
            e.printStackTrace();
        } catch (DatabindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();            
        } 
        return data;
    }
    

    /*
     * Gère la mise à jour des objets Administrator dans data
     * 
     * @param data
     * @return data
     */
    private static Data insertAdministrators(Data data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Administrator[] adminsArray = mapper.readValue(new File(getPath("administrators.json")), Administrator[].class);
            List<Administrator> modifiableList = new ArrayList<>(Arrays.asList(adminsArray));
            data.setAdministrators(modifiableList);
        } catch (StreamReadException e) {
            e.printStackTrace();
        } catch (DatabindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();            
        } 
        return data;
    }
    

    /*
     * Gère la mise à jour des objets Equipment dans data
     * 
     * @param data
     * @return equipments
     */
    private static List<Equipment> insertEquipments(Data data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Equipment> equipments = Arrays.asList(mapper.readValue(new File(getPath("equipments.json")), Equipment[].class));
            managedEquipmentsReferences(equipments, data.getEquipmentTypes()); // On gère les réferences des objets Equipment

            return equipments;
         } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        } 

        return null;
    }

    /*
     * Gère la mise à jour des objets EquipmentType dans data
     * 
     * @param data
     * @return data
     */
    private static Data insertEquipmentTypes(Data data) {
    try {
        ObjectMapper mapper = new ObjectMapper();
        EquipmentType[] typesArray = mapper.readValue(new File(getPath("equipment_types.json")), EquipmentType[].class);
        List<EquipmentType> modifiableList = new ArrayList<>(Arrays.asList(typesArray));
        data.setEquipmentTypes(modifiableList);
    } catch (IOException e) {
        e.printStackTrace();
    }
    return data;
}


    /*
     * Gère la mise à jour des objets Booking dans data
     * 
     * @param data
     * @return data
     */
    private static Data insertBookings(Data data) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Booking> bookings = Arrays.asList(mapper.readValue(new File(getPath("bookings.json")), Booking[].class));

            managedBookingsReferences(bookings, data.getAdministrators(), data.getUsers(), data.getEquipments()); // On gère les réferences
            data.setBookings(bookings);

        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
            
        } 

        return data;
    }

    /* Gestion des réferences entre les objets Equipment et leur EquipmentType 
     * 
     * @param equipments
     * @param equipmentTypes
     * @throws NullPointerException
    */
    private static void managedEquipmentsReferences(List<Equipment> equipments, List<EquipmentType> equipmentTypes) throws NullPointerException {   
        
        for (Equipment equipment : equipments) {
            EquipmentType typeToSet = null;

            Integer idEquipmentType = equipment.getIdType();
            for (EquipmentType type : equipmentTypes) {
                if (type.getId() == idEquipmentType) {
                    typeToSet = type;
                }
            }

            /* Si aucun EquipmentType n'est trouvé */
            if (typeToSet == null) {
                throw new NullPointerException("L'id du type d'equipement defini dans l'equipement d'id " + equipment.getReference() 
                + " ne correspond à aucune EquipmentType existant.");
            }

            equipment.defineReferences(typeToSet);
        }
    }

    /* Gestion des réferences entre les objets Booking et leur Person et les réferences avec leur objet Equipment 
     * 
     * @param bookings
     * @param administrators
     * @param users
     * @param equipments
     * @throws NullPointerException
    */
    private static void managedBookingsReferences(List<Booking> bookings, List<Administrator> administrators, List<User> users, List<Equipment> equipments) throws NullPointerException {
        
        for (Booking booking : bookings) {
            Person emprunterToSet = null;
            Equipment equipmentToSet = null;

            /* Si l'objet Person est du type Admnistrator */
            if (booking.getIdUser() == -1) {
                Integer idPerson = booking.getIdAdministrator();
                for (Administrator administrator : administrators) {
                    if (administrator.getId() == idPerson) {
                        emprunterToSet = administrator;
                    }
                }
            } 
            /* Sinon, l'objet Person est du type User */ 
            else {
                Integer idPerson = booking.getIdUser();
                for (User user : users) {
                    if (user.getId() == idPerson) {
                        emprunterToSet = user;
                    }
                }
            }

            String referenceEquipment = booking.getReferenceEquipment();
            for (Equipment equipment : equipments) {
                if (equipment.getReference().equals(referenceEquipment)) {
                    equipmentToSet = equipment;
                }
            }

            /* Si aucune Person n'est trouvé */
            if (emprunterToSet == null) {
                throw new NullPointerException("L'id de l'emprunteur defini dans la réservation d'id " + booking.getId() 
                + " ne correspond à aucune Person existante.");
            }
            /* Si aucun Equipment n'est trouvé */
            if (equipmentToSet == null) {
                throw new NullPointerException("L'id de l'equipement defini dans la réservation d'id " + booking.getId() 
                + " ne correspond à aucune Equipment existant.");
            }

            booking.defineReferences(emprunterToSet, equipmentToSet);
        }
    }

    public static String getPassword(Person person) {
        ObjectMapper mapper = new ObjectMapper();
    
        try {
            Map<Integer, String> passwordMap = new HashMap<>();
            File passwordsFile = new File(getPath("passwords.json"));
    
            if (passwordsFile.exists()) {
                passwordMap = mapper.readValue(passwordsFile, new TypeReference<Map<Integer, String>>() {});
            }
    
            String password = passwordMap.get(person.getId());
            
            // Vérifiez si le mot de passe est null avant de tenter de le décrypter
            if (password != null) {
                return Encryption.decrypt(password);
            } else {
                // Peut-être lancer une exception personnalisée ou retourner null
                System.err.println("Mot de passe non trouvé pour l'ID : " + person.getId());
            }
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return null;
    }
    
}