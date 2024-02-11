package qualite_log.session;

import qualite_log.data_import.DataReader;
import qualite_log.model.Administrator;
import qualite_log.model.Data;
import qualite_log.model.Person;
import qualite_log.model.User;

public class Authentification {

    /**
     * Tente d'authentifier un administrateur avec le matricule et le mot de passe fournis.
     *
     * @param mail l'email de l'administrateur
     * @param password le mot de passe de l'administrateur
     * @return l'administrateur authentifié, sinon null
     */
    public static Administrator authenticateAdmin(String mail, String password) {
        return Data.getInstance().getAdministrators().stream()
                .filter(admin -> admin.getEmail().equals(mail) && checkPassword(admin, password))
                .findFirst()
                .orElse(null);
    }

    /**
     * Tente d'authentifier un utilisateur avec le matricule et le mot de passe fournis.
     *
     * @param mail l'email de l'utilisateur
     * @param password le mot de passe de l'utilisateur
     * @return l'utilisateur authentifié, sinon null
     */
    public static User authenticateUser(String mail, String password) {
        return Data.getInstance().getUsers().stream()
                .filter(user -> user.getEmail().equals(mail) && checkPassword(user, password))
                .findFirst()
                .orElse(null);
    }

    /**
     * Vérifie si le mot de passe fourni correspond au mot de passe stocké pour l'utilisateur ou l'administrateur.
     *
     * @param person l'utilisateur ou l'administrateur dont il faut vérifier le mot de passe
     * @param password le mot de passe à vérifier
     * @return vrai si le mot de passe correspond, sinon faux
     */
    private static boolean checkPassword(Person person, String password) {
        String storedPassword = DataReader.getPassword(person);
        return password.equals(storedPassword);
    }
}
