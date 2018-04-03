package global;

import generalClasses.Worker;
import global.abstractsClasses.MainFrameGlobalAbstract;

import java.awt.event.ActionEvent;
import java.util.List;

public class MainFrameGlobal extends MainFrameGlobalAbstract {

    private List<Worker> workers = StorageGlobal.getWorkers();

    public MainFrameGlobal(List<Worker> workers) {
        super(workers);
        this.workers = workers;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String res = e.getActionCommand();

        switch (res) {
            case "addWorker":
                new AddWorkerFrameGlobal();
                break;
            case "deleteWorker":
                int selectRow = this.getTable().getSelectedRow();
                if (selectRow > -1) {
                    StorageGlobal.getWorkers().remove(selectRow);
                    MainFrameGlobalAbstract.tableModel.fireTableDataChanged();
                } else {
                    new DeleteWorkerFrameGlobal();
                }
                break;
            case "changeWorker":
                Worker changed = null;
                int selectedRow = this.getTable().getSelectedRow();
                if (selectedRow > -1) {
                    changed  = MainFrameGlobal.getTableModel().workers.get(selectedRow);
                }
                new ChangeWorkerGlobal(changed);
                break;
            case "findWorker":
                //         new FindWorkerFrame();
                break;
            default:
                this.dispose();
                break;
        }
    }
}
