package qualite_log.model;

import java.util.ArrayList;
import java.util.List;

public class Data {
    List<Administrator> administrators;
    List<User> users;
    List<EquipmentType> equipmentTypes;
    List<Booking> bookings;

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

    public Data() {
        administrators = new ArrayList<>();
        users = new ArrayList<>();
        equipmentTypes = new ArrayList<>();
        bookings = new ArrayList<>();
    }
}
