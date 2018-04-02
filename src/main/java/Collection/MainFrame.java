package Collection;

import generalClasses.Worker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.List;

public class MainFrame extends JFrame implements ActionListener, ComponentListener {
    private JButton addWorker;
    private JButton deleteWorker;
    private JButton changeWorker;
    private JButton findWorker;
    private JButton closeProgramm;
    private JTable table;
    private final static String[] header = {"Name", "Surname", "Passport"};

    public static WorkerTableCollection workersTable;

    public MainFrame (List<Worker> al) {

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
        workersTable = new WorkerTableCollection(al, header);
        workersTable.fireTableDataChanged();
        table = new JTable(workersTable);

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
                new AddWorkerFrame();
                break;
            case "deleteWorker":
                int selectRow = table.getSelectedRow();
                if (selectRow > -1) {
                    WorkerTableCollection.workers.remove(selectRow);
                    MainFrame.workersTable.fireTableDataChanged();
                } else {
                    new DeleteWorkerFrame();
                }
                break;
            case "changeWorker":
                Worker changed = null;
                int selectedRow = table.getSelectedRow();
                if (selectedRow > -1) {
                    changed  = WorkerTableCollection.workers.get(selectedRow);
                }
                new ChangeWorkerFrame(changed);
                break;
            case "findWorker":
                new FindWorkerFrame();
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
