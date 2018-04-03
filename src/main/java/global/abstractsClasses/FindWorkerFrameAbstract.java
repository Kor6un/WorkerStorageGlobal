package global.abstractsClasses;

import generalClasses.Worker;
import generalClasses.WorkersTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public abstract class FindWorkerFrameAbstract extends JFrame implements ActionListener {
    private JTextField findField;
    private JComboBox choice;
    private String[] header = {"Name", "Surname", "Passport"};
    private List<Worker> findWorkers = new ArrayList<>();
    private WorkersTableModel findWorkersTable;

    public FindWorkerFrameAbstract(List<Worker> findWorkers) {
        this.findWorkers = findWorkers;

        this.setSize(600, 400);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle("Find worker window");
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(10, 1));

        JLabel findBy = new JLabel("Найти по");
        choice = new JComboBox();
        choice.addItem("по имени");
        choice.addItem("по фамилии");
        choice.addItem("по номеру паспорта");

        findField = new JTextField();

        JButton ok = new JButton("Найти");
        ok.setActionCommand("Find");
        ok.addActionListener(this);
        JButton clear = new JButton("Очистить");
        clear.setActionCommand("Clear");
        clear.addActionListener(this);
        JButton back = new JButton("Назад");
        back.setActionCommand("Back");
        back.addActionListener(this);

        leftPanel.add(findBy);
        leftPanel.add(choice);
        leftPanel.add(findField);
        leftPanel.add(ok);
        leftPanel.add(clear);
        leftPanel.add(back);

        this.add(leftPanel, BorderLayout.WEST);

        JPanel rightPanel = new JPanel();

        rightPanel.setLayout(new GridLayout(1, 1));
        findWorkersTable = new WorkersTableModel(findWorkers, header);
        findWorkersTable.fireTableDataChanged();
        JTable results = new JTable(findWorkersTable);

        rightPanel.add(new JScrollPane(results), BorderLayout.CENTER);

        this.add(rightPanel);
        setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        findWorker();
    }

    protected abstract void findWorker();

    protected JTextField getFindField() {
        return findField;
    }

    public String[] getHeader() {
        return header;
    }

    protected List<Worker> getFindWorkers() {
        return findWorkers;
    }

    protected WorkersTableModel getFindWorkersTable() {
        return findWorkersTable;
    }

    protected JComboBox getChoice() {
        return choice;
    }
}
