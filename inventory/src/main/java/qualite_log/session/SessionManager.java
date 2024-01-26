package qualite_log.session;

import qualite_log.model.Administrator;
import qualite_log.model.User;

public class SessionManager {
    private static Administrator currentAdmin;
    private static User currentUser;

    public static Administrator getCurrentAdmin() {
        return currentAdmin;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentAdmin(Administrator admin) {
        currentAdmin = admin;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static void clearSession() {
        currentAdmin = null;
        currentUser = null;
    }

}