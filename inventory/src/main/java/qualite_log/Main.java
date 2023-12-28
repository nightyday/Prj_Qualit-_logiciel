package qualite_log;

import qualite_log.data_import.DataWriter;

public class Main {
    public static void main(String[] args) {
        DataWriter writer = new DataWriter();

        writer.print();

        writer.extract();
    }
}
