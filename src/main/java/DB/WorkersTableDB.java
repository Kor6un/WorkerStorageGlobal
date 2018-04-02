package DB;

import generalClasses.Worker;

import javax.swing.table.AbstractTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WorkersTableDB extends AbstractTableModel {

    public static String[] header;
    public static List<Worker> workers = new ArrayList<>();
    private static String name;
    private static String surname;
    private static int passport;

    WorkersTableDB(List<Worker> workers, String[] header) {
        this.workers = workers;
        this.header = header;
    }

    public String getColumnName(int c) {
        return header[c];
    }

    public int getRowCount() {
        return workers.size();
    }

    public int getColumnCount() {
        return header.length;
    }

    public Object getValueAt(int r, int c) {
        switch (c) {
            case 0:
                return workers.get(r).getName();
            case 1:
                return workers.get(r).getSurname();
            case 2:
                return workers.get(r).getPassportNumber();
            default:
                return "";
        }
    }
    public static void viewAllWorkers(Connection connection) throws SQLException {
        Statement statement  = connection.createStatement();

        boolean success = statement.execute("SELECT * FROM workers;");
        if (success) {
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                name = resultSet.getString("name");
                surname = resultSet.getString("surname");
                passport  = resultSet.getInt("passport");
                workers.add(new Worker(name, surname, String.valueOf(passport)));
            }
        }
    }
}
