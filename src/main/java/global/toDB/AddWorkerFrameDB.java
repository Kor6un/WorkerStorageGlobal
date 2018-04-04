package global.toDB;

import generalClasses.ExceptionMessage;
import generalClasses.Worker;
import global.StorageGlobal;
import global.abstractsClasses.AddWorkerFrameGlobalAbstract;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddWorkerFrameDB extends AddWorkerFrameGlobalAbstract {

    @Override
    protected void addWorker() {

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Workers",
                    "postgres", "");
            try {
                PreparedStatement statement =
                        connection.prepareStatement("INSERT INTO workers (name, surname, passport) " +
                                "VALUES (?, ?, ?)");

                statement.setString(1, this.getName());
                statement.setString(2, this.getSurname());
                statement.setInt(3, Integer.parseInt(this.getPassportNumber()));

                statement.execute();
            } catch (SQLException e) {
                throw new SQLException("Нарушено ограничение уникальности");
            }
        } catch (Exception e1) {
            new ExceptionMessage("Ошибка подключения к БД (global)");
        }
        StorageGlobal.getWorkers().add(new Worker( getName(), getSurname(), getPassportNumber()));
    }
}
