package txt;

import generalClasses.ExceptionMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DeleteWorkerFrameTXT extends JFrame implements ActionListener{

    private JLabel textPassport;
    private JTextField passportNumber;
    private JButton ok;
    private JButton clear;
    private JButton back;

    public DeleteWorkerFrameTXT() {
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
                for (int i = 0; i < ReaderTXT.getWorkers().size(); i++) {
                    if (passportNumber.getText() != null || !passportNumber.getText().isEmpty()) {
                        if (passportNumber.getText().equals(ReaderTXT.getWorkers().get(i).getPassportNumber())) {
                            ReaderTXT.getWorkers().remove(i);
                            isFind = true;
                        }
                    }
                }
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
                    new ExceptionMessage("Ошибка записи в файл Delete");
                }
                if (!isFind) {
                    new ExceptionMessage("Нет такого работника");
                }
                MainFrameTXT.workerTableTXT.fireTableDataChanged();
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
