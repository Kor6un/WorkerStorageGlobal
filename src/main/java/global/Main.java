package global;

import global.toCollection.MainFrameGlobal;
import global.toCollection.StorageGlobal;

public class Main {
    public static void main(String[] args) {
        new MainFrameGlobal(StorageGlobal.getWorkers());
    }
}
