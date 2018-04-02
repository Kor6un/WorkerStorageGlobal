package txt;

import generalClasses.ExceptionMessage;
import generalClasses.Worker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ChangeWorkerFrameTXT extends JFrame implements ActionListener {
    private JLabel textName;
    private JLabel textSurname;
    private JLabel textPassport;
    private JTextField name;
    private JTextField surname;
    private JTextField passportNumber;
    private JButton ok;
    private JButton clear;
    private JButton back;
    private Worker worker = null;
    public ChangeWorkerFrameTXT(Worker worker) {
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

                if (!name.getText().equals("")
                        && !surname.getText().isEmpty()
                        && !passportNumber.getText().isEmpty() ) {

                    worker.setName(name.getText());
                    worker.setSurname(surname.getText());
                    worker.setPassportNumber(passportNumber.getText());

                    File file1 = new File("src/main/resources/workers.txt");
                    file1.delete();
                    File file = new File("src/main/resources/workers.txt");
                    try (FileWriter fw = new FileWriter(file)){

                        String line;
                        for (int i = 0; i < ReaderTXT.getWorkers().size(); i++){
                            line = ReaderTXT.getWorkers().get(i).getName() + "," +
                                    ReaderTXT.getWorkers().get(i).getSurname() + "," +
                                    ReaderTXT.getWorkers().get(i).getPassportNumber() + "\n" ;
                            fw.write(line);
                            fw.flush();
                        }
                    } catch (IOException e1) {
                        new ExceptionMessage("Ошибка записи в файл change");
                    }

                } else {
                    return;
                }
                MainFrameTXT.workerTableTXT.fireTableDataChanged();
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
}
