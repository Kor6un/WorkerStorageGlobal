package txt;

import generalClasses.ExceptionMessage;
import generalClasses.Worker;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReaderTXT {
    private static List<Worker> workers = new ArrayList<>();
    private static String name, surname, passport;
    private static String resourceName;

    public static List<Worker> getWorkers() {
        return workers;
    }

    public ReaderTXT(String resourcesName) {
        if (resourcesName == null || resourcesName.isEmpty()) {
            new ExceptionMessage("Пустое имя файла");
        }
        this.resourceName = resourcesName;
    }

    public List<Worker> readTXT() {
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream(resourceName);

            try (InputStreamReader reader = new InputStreamReader(is);
                 BufferedReader bufferedReader = new BufferedReader(reader)) {

                String line = bufferedReader.readLine();

                while (line != null) {
                    String[] parts = line.split(",");

                    if (parts.length != 3) {
                        throw new IllegalArgumentException("Неправильное описание ячейки");
                    }

                    name = parts[0];
                    surname = parts[1];
                    passport = parts[2];

                    workers.add(new Worker(name, surname, passport));
                    line = bufferedReader.readLine();
                }
            }catch (Exception e) {
                new ExceptionMessage("Не возможно прочитать файл");
            }
            } catch (Exception e) {
                new ExceptionMessage("Файл не найден!");
            }
        return workers;
    }
}
