package global;

import generalClasses.Worker;
import global.abstractsClasses.WorkersTableModelAbstract;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class WorkersTableModel extends WorkersTableModelAbstract {

    private String[] header;
    private List<Worker> workers = StorageGlobal.getWorkers();

    public WorkersTableModel(List<Worker> workers, String[] header) {
        super(workers, header);
        this.workers = workers;
        this.header = header;
    }

}
