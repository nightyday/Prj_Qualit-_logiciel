package qualite_log.model;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private static Data instance;

    private List<Administrator> administrators;
    private List<User> users;
    private List<EquipmentType> equipmentTypes;
    private List<Booking> bookings;

    public List<Administrator> getAdministrators() {
        return administrators;
    }

    public void setAdministrators(List<Administrator> administrators) {
        this.administrators = administrators;
    }

    public void addAdministrator(Administrator administrator) {
        administrators.add(administrator);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
       this.users = users;
    }

    public void addUsers(User user) {
       users.add(user);
    }
    
    public List<EquipmentType> getEquipmentTypes() {
        return equipmentTypes;
    }
    
    public void setEquipmentTypes(List<EquipmentType> equipmentTypes) {
        this.equipmentTypes = equipmentTypes;
    }

    public void addEquipmentTypes(EquipmentType equipmentType) {
        equipmentTypes.add(equipmentType);
    }
    
    public List<Booking> getBookings() {
        return bookings;
    }
    
    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public void addBookings(Booking booking) {
        bookings.add(booking);
    }
    
    public List<Equipment> getEquipments() {
        List<Equipment> equipments = new ArrayList<>();
        
        for(EquipmentType equipmentType : equipmentTypes) {
            equipments.addAll(equipmentType.getEquipments());
        }

        return equipments;
    }

    private Data() {
        administrators = new ArrayList<>();
        users = new ArrayList<>();
        equipmentTypes = new ArrayList<>();
        bookings = new ArrayList<>();
    }

    /**
     * Méthode à utiliser comme constructeur (pattern singleton)
     * 
     * @return instance
     */
    public static Data getInstance() {
        if(instance == null) {
            instance = new Data();
        }

        return instance;
    }

    public String toString() {
        String str = "";

        str = str + "==========================================\n=== Users : \n";
        for(User user : users) {
            str = str + user.toString() + "\n";
        }

        str = str + "==========================================\n=== Administrators : \n";
        for(Administrator admin : administrators) {
            str = str + admin.toString() + "\n";
        }

        str = str + "==========================================\n=== EquipmentTypes : \n";
        for(EquipmentType type : equipmentTypes) {
            str = str + type.toString() + "\n";
        }

        str = str + "==========================================\n=== Equipments : \n";
        for(Equipment equipment : getEquipments()) {
            str = str + equipment.toString() + "\n";
        }

        str = str + "==========================================\n=== Booking : \n";
        for(Booking booking : bookings) {
            str = str + booking.toString() + "\n";
        }

        return str;
    }
}
