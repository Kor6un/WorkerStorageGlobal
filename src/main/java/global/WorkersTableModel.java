package global;

import generalClasses.Worker;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class WorkersTableModel extends AbstractTableModel {

    public static String[] header;
    public static List<Worker> workers = StorageGlobal.getWorkers();

    public WorkersTableModel(List<Worker> workers, String[] header) {
        this.workers = workers;
        this.header = header;
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
