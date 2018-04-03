package global.abstractsClasses;

import global.toCollection.MainFrameGlobal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class DeleteWorkerFrameAbstract extends JFrame implements ActionListener{

    private JTextField passportNumber;

    public DeleteWorkerFrameAbstract() {
        setSize(400, 150);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Delete worker window");
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,1));

        JLabel textPassport = new JLabel("Enter passport number");
        passportNumber = new JTextField();

        panel.add(textPassport);
        panel.add(passportNumber);

        JPanel container = new JPanel();
        container.setLayout(new GridLayout(1,1));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,3));

        JButton ok = new JButton("Ok");
        ok.setActionCommand("Delete");
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
            case "Delete":
                deleteWorker();
                MainFrameGlobal.getTableModel().fireTableDataChanged();
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

    protected abstract void deleteWorker();

    public JTextField getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(JTextField passportNumber) {
        this.passportNumber = passportNumber;
    }

}
