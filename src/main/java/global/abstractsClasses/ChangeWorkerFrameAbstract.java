package global.abstractsClasses;

import generalClasses.Worker;
import global.toCollection.MainFrameGlobal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class ChangeWorkerFrameAbstract extends JFrame implements ActionListener {

    private JTextField name;
    private JTextField surname;
    private JTextField passportNumber;
    private Worker worker;

    public ChangeWorkerFrameAbstract(Worker worker) {

        this.worker = worker;

        setSize(400, 300);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Change worker window");
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6,1));

        JLabel textName = new JLabel("Enter new name");
        name = new JTextField();
        JLabel textSurname = new JLabel("Enter new surname");
        surname = new JTextField();
        JLabel textPassport = new JLabel("Enter new passport number");
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

        JButton ok = new JButton("Ok");
        ok.setActionCommand("Change");
        ok.addActionListener(this);

        JButton clear = new JButton("Clear");
        clear.setActionCommand("Clear");
        clear.addActionListener(this);

        JButton back = new JButton("Back");
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
                changeWorker();
                MainFrameGlobal.getTableModel().fireTableDataChanged();
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

    protected abstract void changeWorker();

    public String getName() {
        return name.getText();
    }

    public void setName(JTextField name) {
        this.name = name;
    }

    public String getSurname() {
        return surname.getText();
    }

    public void setSurname(JTextField surname) {
        this.surname = surname;
    }

    public String getPassportNumber() {
        return passportNumber.getText();
    }

    public void setPassportNumber(JTextField passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }
}
