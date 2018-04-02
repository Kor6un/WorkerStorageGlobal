package DB;

import generalClasses.ExceptionMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DeleteWorkerFrameDB extends JFrame implements ActionListener{

    private JLabel textPassport;
    private static JTextField passportNumber;
    private JButton ok;
    private JButton clear;
    private JButton back;
    private Connection connection;

    public DeleteWorkerFrameDB() {
        setSize(400, 150);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Delete worker window");
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,1));

        textPassport = new JLabel("Enter passport number");
        passportNumber = new JTextField();

        panel.add(textPassport);
        panel.add(passportNumber);

        JPanel container = new JPanel();
        container.setLayout(new GridLayout(1,1));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,3));

        ok = new JButton("Ok");
        ok.setActionCommand("Delete");
        ok.addActionListener(this);
        clear = new JButton("Clear");
        clear.setActionCommand("Clear");
        clear.addActionListener(this);
        back = new JButton("Back");
        back.setActionCommand("Back");
        back.addActionListener(this);
        buttonPanel.add(ok);
        buttonPanel.add(clear);
        buttonPanel.add(back);

        add(panel,BorderLayout.CENTER);
        container.add(buttonPanel);

        add(container, BorderLayout.SOUTH);

        setVisible(true);
    }

    private static void tableDeleteSQL(Connection connection) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM workers WHERE passport = ?");

            statement.setInt(1, Integer.parseInt(passportNumber.getText()));
            System.out.println(Integer.parseInt(passportNumber.getText()));
            statement.executeUpdate();
        }catch (SQLException e){
            new ExceptionMessage("Такого рабочего нет в БД (delDB)");
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/workersDB",
                    "postgres", "12345");
        } catch (Exception e1) {
            new ExceptionMessage("Ошибка подключения к БД (delDB)");
        }
        String res = e.getActionCommand();
        switch (res) {
            case "Delete":
                boolean isFind = false;
                for (int i = 0; i < WorkersTableDB.workers.size(); i++) {
                    if (passportNumber.getText() != null || !passportNumber.getText().isEmpty()) {
                        if (passportNumber.getText().equals(WorkersTableDB.workers.get(i).getPassportNumber())) {
                            try {
                                tableDeleteSQL(connection);
                                connection.setAutoCommit(false);
                                connection.close();
                                WorkersTableDB.workers.remove(i);
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
                MainFrameDB.model.fireTableDataChanged();
                this.dispose();
                break;
            case "Clear":
                passportNumber.setText("");
                break;
            case "Back":
                this.dispose();
                break;
            default:
                this.dispose();
                break;
        }
    }
}
