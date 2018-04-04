package global.toCollection;

import generalClasses.Worker;
import global.StorageGlobal;
import global.abstractsClasses.AddWorkerFrameGlobalAbstract;

public class AddWorkerFrameGlobal extends AddWorkerFrameGlobalAbstract {

    @Override
    protected void addWorker() {
        StorageGlobal.getWorkers().add(new Worker( getName(), getSurname(), getPassportNumber()));
    }
}
