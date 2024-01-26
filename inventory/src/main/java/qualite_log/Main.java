package qualite_log;

import qualite_log.data_import.DataReader;
import qualite_log.data_import.DataWriter;
import qualite_log.model.Administrator;
import qualite_log.model.Data;
import qualite_log.model.User;

public class Main {
    public static void main(String[] args) {
        Data data = Data.getInstance();

        for(Administrator a : data.getAdministrators()) {
            String pass = "password" + "a" + "0" + a.getId();
            DataWriter.extractPassword(a, pass);
            String str = DataReader.getPassword(a);
            System.out.println(a  + " : " + pass + " => " + str);
        }

        for(User a : data.getUsers()) {
            String pass = "password" + "u" + "0" + a.getId();
            DataWriter.extractPassword(a, pass);
            String str = DataReader.getPassword(a);
            System.out.println(a  + " : " + pass + " => " + str);
        }
    }
}
