package Collection;

import generalClasses.Worker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddWorkerFrame extends JFrame implements ActionListener {

    private JLabel textName, textSurname, textPassport;
    private static JTextField name;
    private static JTextField surname;
    private static JTextField passportNumber;
    private JButton ok, clear, back;

    public AddWorkerFrame() {
        setSize(400, 300);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setTitle("Add worker window");
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6,1));

        textName = new JLabel("Enter name");
        name = new JTextField();
        textSurname = new JLabel("Enter surname");
        surname = new JTextField();
        textPassport = new JLabel("Enter passport number");
        passportNumber = new JTextField();

        panel.add(textName);
        panel.add(name);
        panel.add(textSurname);
        panel.add(surname);
        panel.add(textPassport);
        panel.add(passportNumber);

        JPanel container = new JPanel();
        container.setLayout(new GridLayout(3,1));
        JPanel checkBoxPanel = new JPanel();

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,3));

        ok = new JButton("Ok");
        ok.setActionCommand("Added");
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
        container.add(checkBoxPanel);
        container.add(buttonPanel);

        add(container, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String res = e.getActionCommand();
        switch (res) {
            case "Added":
                if (!name.getText().equals("")
                        && !surname.getText().isEmpty()
                        && !passportNumber.getText().isEmpty() ) {
                    WorkerTableCollection.workers.add(new Worker( name.getText(),
                                                             surname.getText(),
                                                             passportNumber.getText()));

                } else return;
                this.dispose();
                MainFrame.workersTable.fireTableDataChanged();
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
}
