package global.toDB;

import generalClasses.ExceptionMessage;
import global.StorageGlobal;
import global.abstractsClasses.DeleteWorkerFrameAbstract;
import global.toCollection.MainFrameGlobal;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteWorkerFrameDB extends DeleteWorkerFrameAbstract{

    private Connection connection;

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Workers",
                    "postgres", "12345");
        } catch (Exception e1) {
            new ExceptionMessage("Ошибка подключения к БД (delDB)");
        }
        String res = e.getActionCommand();
        switch (res) {
            case "Delete":
                deleteWorker();
                MainFrameGlobal.getTableModel().fireTableDataChanged();
                this.dispose();
                break;
            case "Clear":
                getPassportNumber().setText("");
                break;
            case "Back":
                this.dispose();
                break;
            default:
                this.dispose();
                break;
        }
    }

    private void removeFromDB(Connection connection) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM workers WHERE passport = ?");
            statement.setInt(1, Integer.parseInt(getPassportNumber().getText()));
            statement.executeUpdate();
        }catch (SQLException e){
            new ExceptionMessage("Такого рабочего нет в БД (delDB)");
        }
    }

    @Override
    protected void deleteWorker() {
        boolean isFind = false;
        for (int i = 0; i < StorageGlobal.getWorkers().size(); i++) {
            if (getPassportNumber().getText() != null || !getPassportNumber().getText().isEmpty()) {
                if (getPassportNumber().getText().equals(StorageGlobal.getWorkers().get(i).getPassportNumber())) {
                    try {
                        removeFromDB(connection);
                        connection.setAutoCommit(false);
                        connection.close();
                        StorageGlobal.getWorkers().remove(i);
                        isFind = true;
                    } catch (SQLException e1) {
                        new ExceptionMessage("Рабочего с таким номером пасспорта НЕТ (delDB)");
                    }
                }
            } else {
                return;
            }
        }
        if (!isFind) {
            new ExceptionMessage("ничё не найдено (delDB)");
        }
    }
}
