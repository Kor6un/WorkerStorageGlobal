package global;

import Collection.WorkerTableCollection;
import generalClasses.ExceptionMessage;
import global.abstractsClasses.DeleteWorkerFrameAbstract;

public class DeleteWorkerFrameGlobal extends DeleteWorkerFrameAbstract {


    @Override
    protected void deleteWorker() {

        boolean isFind = false;
        for (int i = 0; i < WorkerTableCollection.workers.size(); i++) {
            if (this.getPassportNumber().getText() != null || !this.getPassportNumber().getText().isEmpty()) {
                if (this.getPassportNumber().getText().equals(StorageGlobal.getWorkers().get(i).getPassportNumber())) {
                    StorageGlobal.getWorkers().remove(i);
                    isFind = true;
                }
            }
        }
        if (!isFind) {
            new ExceptionMessage("Нет такого работника");
        }
    }
}
