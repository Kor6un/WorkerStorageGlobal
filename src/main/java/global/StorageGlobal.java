package global;

import generalClasses.Worker;

import java.util.ArrayList;
import java.util.List;

public class StorageGlobal {
    private static List<Worker> workers = new ArrayList<>();
    private static List<Worker> findWorkers = new ArrayList<>();

    public static List<Worker> getWorkers() {
        return workers;
    }

    public static void setWorkers(List<Worker> workers) {
        StorageGlobal.workers = workers;
    }

    public static List<Worker> getFindWorkers() {
        return findWorkers;
    }

    public static void setFindWorkers(List<Worker> findWorkers) {
        StorageGlobal.findWorkers = findWorkers;
    }
}