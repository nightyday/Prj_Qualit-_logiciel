package qualite_log.data_import;

import java.io.File;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import qualite_log.model.Administrator;
import qualite_log.model.Booking;
import qualite_log.model.Data;
import qualite_log.model.Equipment;
import qualite_log.model.EquipmentType;
import qualite_log.model.User;



public class DataWriter {
    public static void extract(Data data) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        try {        
            String path = "/home/arthur/Documents/Polytech-Tours/S7/Qualité Logiciel/Prj_Qualit-_logiciel/inventory/data/";

            writer.writeValue(new File(path + "users.json"), data.getUsers());

            writer.writeValue(new File(path + "administrators.json"), data.getAdministrators());

            //writer.writeValue(new File(path + "equipments.json"), data.getEquipments());

            writer.writeValue(new File(path + "equipment_types.json"), data.getEquipmentTypes());

            writer.writeValue(new File(path + "bookings.json"), data.getBookings());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DataWriter() {
        Data data = new Data();

        Administrator admin = new Administrator("michel", "dubois", "michel.dubois@univ-tours.fr");
        data.addAdministrator(admin);
        EquipmentType equipT = new EquipmentType();
        data.addEquipmentTypes(equipT);
        Equipment equip = new Equipment(equipT);
        data.addUsers(new User("jhon", "doe", "jhon.doe@etu.univ-tours.fr"));
        data.addBookings(new Booking(admin, equip));        
    }
}