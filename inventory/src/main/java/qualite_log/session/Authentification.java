package qualite_log.session;

import qualite_log.model.Administrator;
import qualite_log.model.Data;
import qualite_log.model.User;
import qualite_log.data_import.DataReader;

import java.util.List;

public class Authentification {

    /**
     * @param matricule
     * @param password
     * @return l'administrateur s'il est authentifié, sinon null
     * 
     */
    public static Administrator authenticateAdmin(String matricule, String password) {
        // Charger les administrateurs depuis la classe Data
        Data data = Data.getInstance();
        List<Administrator> admins = data.getAdministrators();

        // Rechercher l'administrateur avec le matricule donné
        for (Administrator admin : admins) {
            if (admin.getEmail().equals(matricule)) {
                // Récupérer le mot de passe depuis le fichier passwords.json
                String storedPassword = DataReader.getPassword(admin);

                // Vérifier si le mot de passe correspond
                if (password.equals(storedPassword)) {
                    return admin; // Authentification réussie
                }
            }
        }
        return null;
    }

    /**
     * @param matricule
     * @param password
     * @return l'utilisateur s'il est authentifié, sinon null
     */
    public static User authenticateUser(String matricule, String password) {

        // Charger les utilisateurs depuis la classe Data
        Data data = Data.getInstance();
        List<User> users = data.getUsers();

        // Rechercher l'utilisateur avec le matricule donné
        for (User user : users) {
            if (user.getEmail().equals(matricule)) {
                String storedPassword = DataReader.getPassword(user);

                // Vérifier si le mot de passe correspond
                if (password.equals(storedPassword)) {
                    return user; // Authentification réussie
                }
            }
        }
        return null;
    }

}
