package Collection;

import generalClasses.Worker;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
 
public class FindWorkersTable extends AbstractTableModel {

    public static String[] header;
    public static List<Worker> findWorkers = new ArrayList<>();

    FindWorkersTable(List<Worker> findWorkers, String[] header) {
        this.findWorkers = findWorkers;
        this.header = header;
    }

    public String getColumnName(int c) {
       return header[c];
    }

    public int getRowCount() {
        return findWorkers.size();
    }

    public int getColumnCount() {
        return header.length;
    }

    public Object getValueAt(int r, int c) {
        switch (c) {
            case 0:
                return findWorkers.get(r).getName();
            case 1:
                return findWorkers.get(r).getSurname();
            case 2:
                return findWorkers.get(r).getPassportNumber();
            default:
                return "";
        }
    }

}
