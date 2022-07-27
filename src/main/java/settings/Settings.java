package settings;

import java.io.*;
import java.net.URL;

public class Settings {
    private static final File FILE = new File("./src/main/resources/Settings.cfg");

    /*Записывать и читать файл только в одной последовательности
     * иначе получаем RuntimeException.
     * пример правильного вызова:
     * writeSettingsFile(object1,object2,object3)
     * readSettingsFile(object1,object2,object3)
     */
    public static void writeToFile(Object... objects) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE));
        for (Object object : objects) {
            out.writeObject(object);
        }
        out.close();
    }

    public static void loadFromFile(Object... objects) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE));
        for (int i = 0; i < objects.length; i++) {
            objects[i] = in.readObject();
        }
    }
}
