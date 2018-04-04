package global.toDB;

import DB.*;
import generalClasses.ExceptionMessage;
import generalClasses.Worker;
import generalClasses.WorkersTableModel;
import global.StorageGlobal;
import global.abstractsClasses.MainFrameGlobalAbstract;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.sql.*;
import java.util.List;

public class MainFrameDBGlobal extends MainFrameGlobalAbstract{

    private Connection connection;
    private static String nameStr, surnameStr;
    private static int passportStr, id;
    private List<Worker> workers = StorageGlobal.getWorkers();

    public MainFrameDBGlobal(List<Worker> workers) {
        super(workers);
        this.workers = workers;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String res = e.getActionCommand();

        switch (res) {
            case "addWorker":
                new AddWorkerFrameDB();
                break;
            case "deleteWorker":
                int selectRow = this.getTable().getSelectedRow();
                if (selectRow > -1) {
                    try {
                        try {
                            Class.forName("org.postgresql.Driver");
                            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Workers",
                                    "postgres", "");
                        } catch (Exception e1) {
                            new ExceptionMessage("Ошибка подключения к БД (delDB)");
                        }

                        PreparedStatement statement = connection.prepareStatement("DELETE FROM workers" +
                                " WHERE passport = ?");

                        statement.setInt(1,
                                Integer.parseInt(StorageGlobal.getWorkers().get(selectRow).getPassportNumber()));
                        statement.executeUpdate();
                        connection.setAutoCommit(false);
                        connection.close();

                        StorageGlobal.getWorkers().remove(selectRow);
                        getTableModel().fireTableDataChanged();
                    } catch (SQLException e1) {
                        new ExceptionMessage("Такого рабочего нет в БД (delDB)");
                    }
                } else {
                    new DeleteWorkerFrameDB();
                }

                break;
            case "changeWorker":
                try {
                    Class.forName("org.postgresql.Driver");
                    connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/workersDB",
                            "postgres", "");
                } catch (Exception e1) {
                    new ExceptionMessage("Ошибка подключения к БД (delDB)");
                }
                Worker changed = null;
                int selectedRow = this.getTable().getSelectedRow();
                if (selectedRow > -1) {
                    try {
                        PreparedStatement statement  = connection.prepareStatement("SELECT * FROM workers WHERE passport = ?;");
                        statement.setInt(1, Integer.parseInt(WorkersTableDB.workers.get(selectedRow).getPassportNumber()));
                        boolean success = statement.execute();
                        if (success) {
                            ResultSet resultSet = statement.getResultSet();
                            if (resultSet.next()) {
                                nameStr = resultSet.getString("Name");
                                surnameStr = resultSet.getString("surname");
                                passportStr = resultSet.getInt("passport");
                                id = resultSet.getInt("id");
                                System.out.println(id);
                            }
                            changed = new Worker(nameStr, surnameStr, String.valueOf(passportStr));
                        } else {
                            new ExceptionMessage("Выдилите строку в списке");
                            return;
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                new ChangeWorkerFrameDB(changed, id);
                break;
            case "findWorker":
                new FindWorkerFrameDB();
                break;
            default:
                this.dispose();
                break;
        }
    }
}
