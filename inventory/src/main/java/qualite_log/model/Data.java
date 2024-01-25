package qualite_log.model;

import java.util.ArrayList;
import java.util.List;

import qualite_log.data_import.DataReader;

public class Data {
    private static Data instance = null;
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

    private static void updateData() {
        instance = DataReader.insert(instance);
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

            updateData();
        }

        return instance;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append("==========================================\n=== Users : \n");
        for(User user : users) {
            str.append(user.toString() + "\n");
        }

        str.append("==========================================\n=== Administrators : \n");
        for(Administrator admin : administrators) {
            str.append(admin.toString() + "\n");
        }

        str.append("==========================================\n=== EquipmentTypes : \n");
        for(EquipmentType type : equipmentTypes) {
            str.append(type.toString() + "\n");
        }

        str.append("==========================================\n=== Equipments : \n");
        for(Equipment equipment : getEquipments()) {
            str.append(equipment.toString() + "\n");
        }

        str.append("==========================================\n=== Booking : \n");
        for(Booking booking : bookings) {
            str.append(booking.toString() + "\n");
        }

        return new String(str);
    }
}
