package DB;

import generalClasses.ExceptionMessage;
import generalClasses.Worker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FindWorkerFrameDB extends JFrame implements ActionListener {
    private JLabel findBy;
    private static JTextField findField;
    private JButton ok;
    private JButton clear;
    private JButton back;
    private JComboBox choice;

    private JTable results;
    private String[] header = {"Name", "Surname", "Passport"};
    private static List<Worker> findWorkersDB = new ArrayList<>();
    private static FindWorkersTableDB findWorkersTableDB;

    private Connection connection;

    private static String name;
    private static String surname;
    private static int passport;

    public FindWorkerFrameDB() {
        this.setSize(600, 400);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle("Find worker window");
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(10, 1));

        findBy = new JLabel("Найти по");
        choice = new JComboBox();
        choice.addItem("по имени");
        choice.addItem("по фамилии");
        choice.addItem("по номеру паспорта");

        findField = new JTextField();

        ok = new JButton("Найти");
        ok.setActionCommand("Find");
        ok.addActionListener(this);
        clear = new JButton("Очистить");
        clear.setActionCommand("Clear");
        clear.addActionListener(this);
        back = new JButton("Назад");
        back.setActionCommand("Back");
        back.addActionListener(this);

        leftPanel.add(findBy);
        leftPanel.add(choice);
        leftPanel.add(findField);
        leftPanel.add(ok);
        leftPanel.add(clear);
        leftPanel.add(back);

        this.add(leftPanel, BorderLayout.WEST);

        JPanel rightPanel = new JPanel();

        rightPanel.setLayout(new GridLayout(1, 1));
        findWorkersTableDB = new FindWorkersTableDB(findWorkersDB, header);
        findWorkersTableDB.fireTableDataChanged();
        results = new JTable(findWorkersTableDB);

        rightPanel.add(new JScrollPane(results), BorderLayout.CENTER);

        this.add(rightPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int result = choice.getSelectedIndex();
        String res = e.getActionCommand();

        switch (res) {
            case "Find":
                if (findField.getText().isEmpty()){
                    new ExceptionMessage("Заполните поле поиска");
                } else {
                    if (result == 0) {
                        findByName();
                    } else if (result == 1) {
                        findBySurname();
                    } else if (result == 2) {
                        findByPassport();
                    }
                }
                break;
            case "Clear":
                clear();
                break;
            case "Back":
                this.dispose();
                break;
            default:
                this.dispose();
                break;
        }
    }

    private void clear() {
        findField.setText("");
        findWorkersDB.clear();
        findWorkersTableDB.fireTableDataChanged();
    }

    public static void findByNameDB(Connection connection) throws SQLException {
        PreparedStatement statement  = connection.prepareStatement("SELECT * FROM workers WHERE name = ?;");
        statement.setString(1, findField.getText());

        boolean success = statement.execute();
        if (success) {
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                name = resultSet.getString("Name");
                surname = resultSet.getString("surname");
                passport  = resultSet.getInt("passport");
                findWorkersDB.add(new Worker(name, surname, String.valueOf(passport)));
            }
            if (findWorkersDB.size() == 0){
                new ExceptionMessage("Рабочего с таким именем нет в БД");
            }
        }
    }

    private void findByName() {

        if (WorkersTableDB.workers.isEmpty()){
            new ExceptionMessage("Список рабочих пуст (FindFrameDB)");
            this.dispose();
        } else {

            if (!findWorkersDB.isEmpty()){
                findWorkersDB.clear();
            }

            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/workersDB",
                        "postgres", "12345");

                findByNameDB(connection);

                connection.setAutoCommit(false);
                connection.close();

            } catch (Exception e1) {
                new ExceptionMessage("Ошибка подключения к БД (findDB)");
            }
            findWorkersTableDB.fireTableDataChanged();
        }
    }

    private void findBySurname() {
        if (WorkersTableDB.workers.isEmpty()){
            new ExceptionMessage("Список рабочих пуст (FindFrameDB)");
            this.dispose();
        } else {

            if (!findWorkersDB.isEmpty()){
                findWorkersDB.clear();
            }

            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/workersDB",
                        "postgres", "12345");

                findBySurnameDB(connection);

                connection.setAutoCommit(false);
                connection.close();

            } catch (Exception e1) {
                new ExceptionMessage("Ошибка подключения к БД (findDB)");
            }
            findWorkersTableDB.fireTableDataChanged();
        }
    }

    private void findBySurnameDB(Connection connection) throws SQLException {
        PreparedStatement statement  = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM workers WHERE surname = ?;");
        } catch (SQLException e) {
            new ExceptionMessage("Карявый запрос!");
        }
        try {
            statement.setString(1, findField.getText());
        } catch (SQLException e) {
            new ExceptionMessage("Неверный параметр запроса");
        }

        boolean success = false;
        try {
            success = statement.execute();
        } catch (SQLException e) {
            new ExceptionMessage("Ошибка ответа из БД");
        }
        if (success) {
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                name = resultSet.getString("Name");
                surname = resultSet.getString("surname");
                passport  = resultSet.getInt("passport");
                findWorkersDB.add(new Worker(name, surname, String.valueOf(passport)));
            }
            if (findWorkersDB.size() == 0){
                new ExceptionMessage("Рабочего с такой фамилией нет в БД");
            }
        }
    }

    private void findByPassport() {
        if (WorkersTableDB.workers.isEmpty()) {
            new ExceptionMessage("Список рабочих пуст (FindFrameDB)");
            this.dispose();
        } else {

            if (!findWorkersDB.isEmpty()) {
                findWorkersDB.clear();
            }

            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/workersDB",
                        "postgres", "12345");

                findByPassportDB(connection);

                connection.setAutoCommit(false);
                connection.close();

            } catch (Exception e1) {
                new ExceptionMessage("Ошибка подключения к БД (findDB)");
            }
            findWorkersTableDB.fireTableDataChanged();
        }
    }

    private void findByPassportDB(Connection connection) throws SQLException {
        PreparedStatement statement  = connection.prepareStatement("SELECT * FROM workers WHERE passport = ?;");
        statement.setInt(1, Integer.parseInt(findField.getText()));

        boolean success = statement.execute();
        if (success) {
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                name = resultSet.getString("Name");
                surname = resultSet.getString("surname");
                passport  = resultSet.getInt("passport");
                findWorkersDB.add(new Worker(name, surname, String.valueOf(passport)));
            }
            if (findWorkersDB.size() == 0){
                new ExceptionMessage("Рабочего с таким номером паспорта нет в БД");
            }
        }
    }
}
