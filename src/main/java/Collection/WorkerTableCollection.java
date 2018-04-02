package Collection;

import generalClasses.Worker;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class WorkerTableCollection extends AbstractTableModel {

    public static String[] header;
    public static List<Worker> workers = new ArrayList<>();

    WorkerTableCollection(List<Worker> workers, String[] header) {
        this.workers = workers;
        this.header = header;
        workers.add(new Worker("Yury", "Korsun", "3213215"));
        workers.add(new Worker("Anton", "Yanchilik", "1234567"));
        workers.add(new Worker("Kor6un", "Korsun", "7654321"));
        workers.add(new Worker("Zmey", "Gorinich", "1212123"));
        workers.add(new Worker("Zina", "Melnikova", "2154879"));
        workers.add(new Worker("Vova", "Volinec", "9865326"));

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
