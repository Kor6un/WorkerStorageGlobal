package global.abstractsClasses;

import generalClasses.Worker;
import global.toCollection.WorkersTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.List;

public abstract class MainFrameGlobalAbstract extends JFrame implements ActionListener, ComponentListener {

    private static JButton addWorker;
    private static JButton deleteWorker;
    private static JButton changeWorker;
    private static JButton findWorker;
    private static JButton closeProgramm;
    private static JTable table;
    private final static String[] header = {"Name", "Surname", "Passport"};

    public static WorkersTableModel tableModel;

    public MainFrameGlobalAbstract(List<Worker> workers) {

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
        tableModel = new WorkersTableModel(workers, header);
        tableModel.fireTableDataChanged();
        table = new JTable(tableModel);

        leftPanel.add(new JScrollPane(table), BorderLayout.CENTER);

        this.add(leftPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.EAST);

        this.addComponentListener(null);
        this.setVisible(true);
    }

    public JButton getAddWorker() {
        return addWorker;
    }

    public void setAddWorker(JButton addWorker) {
        this.addWorker = addWorker;
    }

    public JButton getDeleteWorker() {
        return deleteWorker;
    }

    public void setDeleteWorker(JButton deleteWorker) {
        this.deleteWorker = deleteWorker;
    }

    public JButton getChangeWorker() {
        return changeWorker;
    }

    public void setChangeWorker(JButton changeWorker) {
        this.changeWorker = changeWorker;
    }

    public JButton getFindWorker() {
        return findWorker;
    }

    public void setFindWorker(JButton findWorker) {
        this.findWorker = findWorker;
    }

    public JButton getCloseProgramm() {
        return closeProgramm;
    }

    public void setCloseProgramm(JButton closeProgramm) {
        this.closeProgramm = closeProgramm;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public static String[] getHeader() {
        return header;
    }

    public static WorkersTableModel getTableModel() {
        return tableModel;
    }

    public static void setTableModel(WorkersTableModel tableModel) {
        MainFrameGlobalAbstract.tableModel = tableModel;
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
