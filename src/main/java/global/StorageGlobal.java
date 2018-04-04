package global;

import generalClasses.Worker;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StorageGlobal {
    private static List<Worker> workers = new ArrayList<>();
    private static List<Worker> findWorkers = new ArrayList<>();

    public static List<Worker> getWorkers() {
        return workers;
    }

    public static void setWorkers(List<Worker> workers) {
        StorageGlobal.workers = workers;
    }

    public static List<Worker> getFindWorkers() {
        return findWorkers;
    }

    public static void viewAllWorkers(Connection connection) throws SQLException {
        Statement statement  = connection.createStatement();

        boolean success = statement.execute("SELECT * FROM workers;");
        if (success) {
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int passport  = resultSet.getInt("passport");
                workers.add(new Worker(name, surname, String.valueOf(passport)));
            }
        }
    }

}
