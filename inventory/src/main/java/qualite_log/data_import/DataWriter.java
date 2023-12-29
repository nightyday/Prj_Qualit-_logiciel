package qualite_log.data_import;

import java.io.File;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import qualite_log.model.Data;

public class DataWriter {
    /*
     * Méthode gérant la sérialisation de l'ensemble des données
     * 
     * @param data
    */
    public static void extract(Data data) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        try {        
            String path = "/home/arthur/Documents/Polytech-Tours/S7/Qualité Logiciel/Prj_Qualit-_logiciel/inventory/data/";

            writer.writeValue(new File(path + "users.json"), data.getUsers());
            writer.writeValue(new File(path + "administrators.json"), data.getAdministrators());
            writer.writeValue(new File(path + "equipments.json"), data.getEquipments());
            writer.writeValue(new File(path + "equipment_types.json"), data.getEquipmentTypes());
            writer.writeValue(new File(path + "bookings.json"), data.getBookings());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}