package generalClasses;

import Collection.MainFrame;
import Collection.WorkerTableCollection;
import DB.MainFrameDB;
import DB.WorkersTableDB;
import txt.MainFrameTXT;
import txt.ReaderTXT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Storage extends JFrame implements ActionListener{

    private final String RESOURCE_NAME = "workers.txt";
    private ReaderTXT readerTXT;

    private JButton collection, DB, txt;
    private JLabel label;

    public Storage() {
        this.setTitle("Главное окно");
        this.setSize(300,200);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        label = new JLabel("Выбирите вариант", SwingConstants.CENTER);
        collection = new JButton("Работа с коллекцей");
        collection.setActionCommand("coll");
        collection.addActionListener(this);
        DB = new JButton("Работа с БД");
        DB.setActionCommand("DB");
        DB.addActionListener(this);
        txt = new JButton("Работа с txt-файлом");
        txt.setActionCommand("txt");
        txt.addActionListener(this);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,1));
        panel.add(label);
        panel.add(collection);
        panel.add(DB);
        panel.add(txt);
        add(panel, BorderLayout.CENTER);


        this.setVisible(true);
    }

    private void readTXT() {
        readerTXT = new ReaderTXT(RESOURCE_NAME);
        readerTXT.readTXT();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String res = e.getActionCommand();
        switch (res) {
            case "coll":
                new MainFrame(WorkerTableCollection.workers);
                this.dispose();
                break;
            case "DB":
                try {
                    Class.forName("org.postgresql.Driver");
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
                Connection connection = null;
                try {
                    connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/workersDB",
                            "postgres", "12345");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                try {
                    WorkersTableDB.viewAllWorkers(connection);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                new MainFrameDB(WorkersTableDB.workers);
                try {
                    connection.setAutoCommit(false);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                try {
                    connection.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                this.dispose();
                break;
            case "txt":
                readTXT();
                new MainFrameTXT(ReaderTXT.getWorkers());
                this.dispose();
                break;
        }
    }
}
