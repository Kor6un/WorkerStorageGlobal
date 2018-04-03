package global.abstractsClasses;

import global.toCollection.MainFrameGlobal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class DeleteWorkerFrameAbstract extends JFrame implements ActionListener{

    private JLabel textPassport;
    private JTextField passportNumber;
    private JButton ok;
    private JButton clear;
    private JButton back;

    public DeleteWorkerFrameAbstract() {
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


    public JLabel getTextPassport() {
        return textPassport;
    }

    public void setTextPassport(JLabel textPassport) {
        this.textPassport = textPassport;
    }

    public JTextField getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(JTextField passportNumber) {
        this.passportNumber = passportNumber;
    }

    public JButton getOk() {
        return ok;
    }

    public void setOk(JButton ok) {
        this.ok = ok;
    }

    public JButton getClear() {
        return clear;
    }

    public void setClear(JButton clear) {
        this.clear = clear;
    }

    public JButton getBack() {
        return back;
    }

    public void setBack(JButton back) {
        this.back = back;
    }
}
