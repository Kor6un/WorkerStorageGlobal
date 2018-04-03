package global.toCollection;

import generalClasses.ExceptionMessage;
import generalClasses.Worker;
import global.abstractsClasses.FindWorkerFrameAbstract;

import java.awt.event.ActionEvent;
import java.util.List;

public class FindWorkerFrameGlobal extends FindWorkerFrameAbstract{


    public FindWorkerFrameGlobal(List<Worker> findWorkers) {
        super(findWorkers);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String res = e.getActionCommand();

        switch (res) {
            case "Find":
                findWorker();
                break;
            case "Clear":
                this.getFindField().setText("");
                this.getFindWorkers().clear();
                this.getFindWorkersTable().fireTableDataChanged();
                break;
            case "Back":
                this.dispose();
                break;
            default:
                this.dispose();
                break;
        }
    }

    @Override
    protected void findWorker() {

        int result = this.getChoice().getSelectedIndex();

        if (result == 0) {
            findByName();
        } else if (result == 1) {
            findBySurname();
        } else if (result == 2) {
            findByPassport();
        }
    }

    private void findByName() {

        if (!StorageGlobal.getFindWorkers().isEmpty()){
            StorageGlobal.getFindWorkers().clear();
        }

        String name = this.getFindField().getText();

        for (int i = 0; i < StorageGlobal.getWorkers().size(); i++) {
            if (StorageGlobal.getWorkers().get(i).getName().equals(name)) {
                StorageGlobal.getFindWorkers().add(StorageGlobal.getWorkers().get(i));
            }
        }

        this.getFindWorkersTable().fireTableDataChanged();

        if (StorageGlobal.getFindWorkers().isEmpty()) {
            new ExceptionMessage("Ничего не найдено");
        }
    }

    private void findBySurname() {

        if (!StorageGlobal.getFindWorkers().isEmpty()){
            StorageGlobal.getFindWorkers().clear();
        }

        String surname = this.getFindField().getText();

        for (int i = 0; i < StorageGlobal.getWorkers().size(); i++) {
            if (StorageGlobal.getWorkers().get(i).getSurname().equals(surname)) {
                StorageGlobal.getFindWorkers().add(StorageGlobal.getWorkers().get(i));
            }
        }

        this.getFindWorkersTable().fireTableDataChanged();

        if (StorageGlobal.getFindWorkers().isEmpty()) {
            new ExceptionMessage("Ничего не найдено");
        }
    }

    private void findByPassport() {

        if (!StorageGlobal.getFindWorkers().isEmpty()){
            StorageGlobal.getFindWorkers().clear();
        }

        String passport = this.getFindField().getText();

        for (int i = 0; i < StorageGlobal.getWorkers().size(); i++) {
            if (StorageGlobal.getWorkers().get(i).getPassportNumber().equals(passport)) {
                StorageGlobal.getFindWorkers().add(StorageGlobal.getWorkers().get(i));
            }
        }

        this.getFindWorkersTable().fireTableDataChanged();

        if (StorageGlobal.getFindWorkers().isEmpty()) {
            new ExceptionMessage("Ничего не найдено");
        }
    }
}
