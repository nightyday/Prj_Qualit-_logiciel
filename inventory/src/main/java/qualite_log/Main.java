package qualite_log;

import java.util.List;

import qualite_log.data_import.DataReader;
import qualite_log.model.Administrator;
import qualite_log.model.Data;
import qualite_log.model.User;

public class Main { 
   /**
     * Méthode main pour afficher les informations d'identification des admin/user
     *  a des fins de débogage/tests.
     *
     * @param args
     */
    public static void main(String[] args) {
       Data data = Data.getInstance();
       List<User> l = data.getUsers();
       //List<Administrator> l = data.getAdministrators();
       for(User a : l){
           System.out.println(a.getEmail());
           String pwd =DataReader.getPassword(a);
           System.out.println("password : "+pwd);

         }
    }
}
