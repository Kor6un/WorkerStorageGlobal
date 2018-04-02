package txt;

import generalClasses.ExceptionMessage;
import generalClasses.Worker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MainFrameTXT extends JFrame implements ActionListener, ComponentListener {
    private JButton addWorker;
    private JButton deleteWorker;
    private JButton changeWorker;
    private JButton findWorker;
    private JButton closeProgramm;
    private JTable table;
    private final static String[] header = {"Name", "Surname", "Passport"};

    public static WorkerTableTXT workerTableTXT;

    public MainFrameTXT(List<Worker> workers) {

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

        leftPanel.setLayout(new BorderLayout());
        workerTableTXT = new WorkerTableTXT(workers, header);
        workerTableTXT.fireTableDataChanged();
        table = new JTable(workerTableTXT);

        leftPanel.add(new JScrollPane(table), BorderLayout.CENTER);

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
                new AddWorkerFrameTXT();
                break;
            case "deleteWorker":
                int selectRow = table.getSelectedRow();
                if (selectRow > -1) {
                    ReaderTXT.getWorkers().remove(selectRow);
                    MainFrameTXT.workerTableTXT.fireTableDataChanged();

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

                } else {
                    new DeleteWorkerFrameTXT();
                }
                break;
            case "changeWorker":
                Worker changed = null;
                int selectedRow = table.getSelectedRow();
                if (selectedRow > -1) {
                    changed  = WorkerTableTXT.workers.get(selectedRow);
                }
                new ChangeWorkerFrameTXT(changed);
                break;
            case "findWorker":
                new FindWorkerFrameTXT();
                break;
            default:
                this.dispose();
                break;
        }
    }

    @Override
    public void componentResized(ComponentEvent componentEvent) {
        revalidate();
    }

    @Override
    public void componentMoved(ComponentEvent componentEvent) {
        revalidate();
    }

    @Override
    public void componentShown(ComponentEvent componentEvent) {
        revalidate();
    }

    @Override
    public void componentHidden(ComponentEvent componentEvent) {
        revalidate();
    }
}
