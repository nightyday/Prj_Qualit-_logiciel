package qualite_log.data_import;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import qualite_log.model.Data;
import qualite_log.model.Person;
import qualite_log.tool.Encryption;

public class DataWriter {
    /*
     * Constructeur pour résoudre la recomendation suivante : 
     * 
     * Add a private constructor to hide the implicit public one.
     * 
    */
    private DataWriter() {}
    
    /*
     * Méthode permettant de récupérer le chemin vers un fichier json du dossier data
     * 
     * @param fileName
     * @return le chemin vers fileName
     */
    private static String getPath(String fileName) {
        String currentDirectory = System.getProperty("user.dir");

        return currentDirectory + "/inventory/data/" + fileName;
    }

    /*
     * Méthode permettant de récupérer le chemin vers un fichier json du dossier data
     * 
     * @param fileName
     * @param listToSerialize
     * @return le chemin vers fileName
     */
    private static void extractAndWrite(String fileName, Object listToSerialize) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        try {
            writer.writeValue(new File(getPath(fileName)), listToSerialize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Méthode gérant la sérialisation de l'ensemble des données
     * 
     * @param data
    */
    public static void extract(Data data) {
        extractAndWrite("users.json", data.getUsers());
        extractAndWrite("administrators.json", data.getAdministrators());
        extractAndWrite("equipments.json", data.getEquipments());
        extractAndWrite("equipment_types.json", data.getEquipmentTypes());
        extractAndWrite("bookings.json", data.getBookings());
    }

    /*
     * Méthode gérant la sérialisation des objets User
     * 
     * @param data
    */
    public static void extractUsers(Data data) {
        extractAndWrite("users.json", data.getUsers());
    }

    /*
     * Méthode gérant la sérialisation des objets Administrator
     * 
     * @param data
    */
    public static void extractAdministrators(Data data) {
        extractAndWrite("administrators.json", data.getAdministrators());
    }

    /*
     * Méthode gérant la sérialisation des objets Equipment
     * 
     * @param data
    */
    public static void extractEquipments(Data data) {
        extractAndWrite("equipments.json", data.getEquipments());
    }

    /*
     * Méthode gérant la sérialisation des objets EquipmentType
     * 
     * @param data
    */
    public static void extractEquipmentTypes(Data data) {
        extractAndWrite("equipment_types.json", data.getEquipmentTypes());
    }

    /*
     * Méthode gérant la sérialisation des objets Booking
     * 
     * @param data
    */
    public static void extractBookings(Data data) {
        extractAndWrite("bookings.json", data.getBookings());
    }

   public static void extractPassword(Person person, String password) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            Map<Integer, String> existingMap = new HashMap<>();

            File passwordsFile = new File(getPath("passwords.json"));
            if (passwordsFile.exists()) {
                existingMap = mapper.readValue(passwordsFile, new TypeReference<Map<Integer, String>>() {});
            }

            existingMap.put(person.getId(), Encryption.encrypt(password));

            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(passwordsFile, existingMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}