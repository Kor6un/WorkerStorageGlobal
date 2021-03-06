package global.toCollection;

import generalClasses.Worker;
import global.abstractsClasses.ChangeWorkerFrameAbstract;

public class ChangeWorkerGlobal extends ChangeWorkerFrameAbstract {
    private Worker worker;

    ChangeWorkerGlobal(Worker worker) {
        super(worker);
        this.worker = worker;
    }

    @Override
    protected void changeWorker() {

        if (!this.getName().equals("")
                && !this.getSurname().isEmpty()
                && !this.getPassportNumber().isEmpty()) {

            worker.setName(this.getName());
            worker.setSurname(this.getSurname());
            worker.setPassportNumber(this.getPassportNumber());
        } else {
            return;
        }
    }
}
