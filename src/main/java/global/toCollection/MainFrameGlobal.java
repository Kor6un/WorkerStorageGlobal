package global.toCollection;

import generalClasses.ExceptionMessage;
import generalClasses.Worker;
import global.abstractsClasses.MainFrameGlobalAbstract;

import java.awt.event.ActionEvent;
import java.util.List;

public class MainFrameGlobal extends MainFrameGlobalAbstract {

    public MainFrameGlobal(List<Worker> workers) {
        super(workers);
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
                    getTableModel().fireTableDataChanged();
                } else {
                    new DeleteWorkerFrameGlobal();
                }
                break;
            case "changeWorker":
                if (!StorageGlobal.getWorkers().isEmpty()) {
                    Worker changed;
                    int selectedRow = this.getTable().getSelectedRow();
                    if (selectedRow > -1) {
                        changed  = getTableModel().getWorkers().get(selectedRow);
                    } else {
                        new ExceptionMessage("Не выбран рабочий для изменения");
                        return;
                    }
                    new ChangeWorkerGlobal(changed);
                } else {
                    new ExceptionMessage("Список работников пуст");
                    return;
                }
                break;
            case "findWorker":
                new FindWorkerFrameGlobal(StorageGlobal.getFindWorkers());
                break;
            default:
                this.dispose();
                break;
        }
    }
}
