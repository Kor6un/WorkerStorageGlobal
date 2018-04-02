package DB;

import generalClasses.ExceptionMessage;
import generalClasses.Worker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ChangeWorkerFrameDB extends JFrame implements ActionListener {
    private JLabel textName;
    private JLabel textSurname;
    private JLabel textPassport;
    private JTextField name;
    private JTextField surname;
    private JTextField passportNumber;
    private JButton ok;
    private JButton clear;
    private JButton back;
    private Worker worker;
    private Connection connection;
    private int id;

    public ChangeWorkerFrameDB(Worker worker, int id) {
        this.id = id;
        this.worker = worker;
        setSize(400, 300);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Change worker window");
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6,1));

        textName = new JLabel("Enter new name");
        name = new JTextField();
        textSurname = new JLabel("Enter new surname");
        surname = new JTextField();
        textPassport = new JLabel("Enter new passport number");
        passportNumber = new JTextField();
        name.setText(worker.getName());
        surname.setText(worker.getSurname());
        passportNumber.setText(worker.getPassportNumber());
        panel.add(textName);
        panel.add(name);
        panel.add(textSurname);
        panel.add(surname);
        panel.add(textPassport);
        panel.add(passportNumber);

        JPanel container = new JPanel();
        container.setLayout(new GridLayout(3,1));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,3));

        ok = new JButton("Ok");
        ok.setActionCommand("Change");
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

    @Override
    public void actionPerformed(ActionEvent e) {

        String res = e.getActionCommand();
        switch (res) {
            case "Change":
                try {
                    Class.forName("org.postgresql.Driver");
                    connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/workersDB",
                        "postgres", "12345");
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                if (!name.getText().equals("")
                        && !surname.getText().isEmpty()
                        && !passportNumber.getText().isEmpty() ) {
                    try {
                        updateBD(connection);
                        connection.setAutoCommit(false);
                        connection.close();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    for (int i = 0; i <WorkersTableDB.workers.size() ; i++) {
                        if (WorkersTableDB.workers.get(i).getPassportNumber().equals(worker.getPassportNumber())) {
                            WorkersTableDB.workers.get(i).setName(name.getText());
                            WorkersTableDB.workers.get(i).setSurname(surname.getText());
                            WorkersTableDB.workers.get(i).setPassportNumber(passportNumber.getText());
                            break;
                        }

                    }

                } else {
                    return;
                }
                MainFrameDB.model.fireTableDataChanged();
                this.dispose();
                break;
            case "Clear":
                name.setText("");
                surname.setText("");
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

    private void updateBD(Connection connection) throws SQLException {
        try {
            PreparedStatement statement  = connection.prepareStatement("UPDATE workers SET name = ?, " +
                    "surname = ?, passport = ? WHERE id = ?;");
            statement.setString(1, name.getText());
            statement.setString(2, surname.getText());
            statement.setInt(3, Integer.parseInt(passportNumber.getText()));
            statement.setInt(4, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            new ExceptionMessage("Херовый запрос");
        }
    }
}
