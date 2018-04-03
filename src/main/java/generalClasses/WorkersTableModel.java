package generalClasses;

import generalClasses.Worker;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class WorkersTableModel extends AbstractTableModel {

    private String[] header;
    private List<Worker> workers = new ArrayList<>();

    public WorkersTableModel(List<Worker> workers, String[] header) {
        this.workers = workers;
        this.header = header;
    }

    public String[] getHeader() {
        return header;
    }

    public void setHeader(String[] header) {
        this.header = header;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    public String getColumnName(int c) {
       return header[c];
    }

    public int getRowCount() {
        return workers.size();
    }

    public int getColumnCount() {
        return header.length;
    }

    public Object getValueAt(int r, int c) {
        switch (c) {
            case 0:
                return workers.get(r).getName();
            case 1:
                return workers.get(r).getSurname();
            case 2:
                return workers.get(r).getPassportNumber();
            default:
                return "";
        }
    }

}
