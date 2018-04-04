package global;

import generalClasses.ExceptionMessage;
import global.toCollection.MainFrameGlobal;
import global.toDB.MainFrameDBGlobal;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {
        //new MainFrameGlobal(StorageGlobal.getWorkers());

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Workers",
                    "postgres", "");

            StorageGlobal.viewAllWorkers(connection);
        } catch (Exception e1) {
            new ExceptionMessage("Ошибка подключения к БД (delDB)");
        }

        new MainFrameDBGlobal(StorageGlobal.getWorkers());
    }
}
