package global.toCollection;

import generalClasses.Worker;
import global.abstractsClasses.WorkersTableModelAbstract;

import java.util.List;

public class FindTableModel extends WorkersTableModelAbstract {

    public FindTableModel(List<Worker> workers, String[] header) {
        super(workers, header);
    }
}
