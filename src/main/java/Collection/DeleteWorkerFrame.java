package Collection;

import generalClasses.ExceptionMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteWorkerFrame extends JFrame implements ActionListener{

    private JLabel textPassport;
    private JTextField passportNumber;
    private JButton ok;
    private JButton clear;
    private JButton back;

    public DeleteWorkerFrame () {
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
                boolean isFind = false;
                for (int i = 0; i < WorkerTableCollection.workers.size(); i++) {
                    if (passportNumber.getText() != null || !passportNumber.getText().isEmpty()) {
                        if (passportNumber.getText().equals(WorkerTableCollection.workers.get(i).getPassportNumber())) {
                            WorkerTableCollection.workers.remove(i);
                            isFind = true;
                        }
                    }
                }
                if (!isFind) {
                    new ExceptionMessage("Нет такого работника");
                }
                MainFrame.workersTable.fireTableDataChanged();
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
