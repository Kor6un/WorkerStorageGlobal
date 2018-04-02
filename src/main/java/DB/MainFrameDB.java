package DB;

import generalClasses.ExceptionMessage;
import generalClasses.Worker;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.sql.*;
import java.util.List;

public class MainFrameDB extends JFrame implements ActionListener, ComponentListener {

    private JButton addWorker, deleteWorker, changeWorker, findWorker, closeProgramm;
    public final static String[] header = {"Name", "Surname", "Passport"};
    private JTable table;
    public static WorkersTableDB model;
    private Connection connection;
    private static String nameStr, surnameStr;
    private static int passportStr, id;

    public MainFrameDB(List<Worker> workers) {
        this.setSize(800,500);
        this.setTitle("Workers storage");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);

        JPanel buttonPanel = new JPanel();

        buttonPanel.setLayout(new GridLayout(5,1));
        addWorker = new JButton("Добавить работника");
        addWorker.setActionCommand("addWorker");
        addWorker.addActionListener(this);
        deleteWorker = new JButton("Удалить работника");
        deleteWorker.setActionCommand("deleteWorker");
        deleteWorker.addActionListener(this);
        changeWorker = new JButton("Изменить работника");
        changeWorker.setActionCommand("changeWorker");
        changeWorker.addActionListener(this);

        findWorker = new JButton("Найти работника");
        findWorker.setActionCommand("findWorker");
        findWorker.addActionListener(this);
        closeProgramm = new JButton("Закрыть программу");
        closeProgramm.addActionListener(this);

        buttonPanel.add(addWorker);
        buttonPanel.add(deleteWorker);
        buttonPanel.add(changeWorker);
        buttonPanel.add(findWorker);
        buttonPanel.add(closeProgramm);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(1, 1));
        model = new WorkersTableDB(workers, header);

        table = new JTable(model);
        leftPanel.add(new JScrollPane(table));
        this.add(leftPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.EAST);

        this.addComponentListener(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String res = e.getActionCommand();

        switch (res) {
            case "addWorker":
                new AddWorkerFrameDB();
                break;
            case "deleteWorker":

                int selectRow = table.getSelectedRow();
                if (selectRow > -1) {
                    try {
                        try {
                            Class.forName("org.postgresql.Driver");
                            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/workersDB",
                                    "postgres", "");
                        } catch (Exception e1) {
                            new ExceptionMessage("Ошибка подключения к БД (delDB)");
                        }

                        PreparedStatement statement = connection.prepareStatement("DELETE FROM workers" +
                                " WHERE passport = ?");

                        statement.setInt(1,
                                Integer.parseInt(WorkersTableDB.workers.get(selectRow).getPassportNumber()));
                        statement.executeUpdate();
                        connection.setAutoCommit(false);
                        connection.close();

                        WorkersTableDB.workers.remove(selectRow);
                        MainFrameDB.model.fireTableDataChanged();
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
                int selectedRow = table.getSelectedRow();
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

    @Override
    public void componentResized(ComponentEvent e) {
        revalidate();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        revalidate();
    }

    @Override
    public void componentShown(ComponentEvent e) {
        revalidate();
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        revalidate();
    }
}
