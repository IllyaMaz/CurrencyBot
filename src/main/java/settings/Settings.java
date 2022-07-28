package settings;

import java.io.*;

public class Settings {
    private static final File FILE = new File("./src/main/resources/Settings.cfg");
    public static BankSetting bankSetting = new BankSetting();
    public static NumberSimbolsAfterCommaSetting digitsSetting = new NumberSimbolsAfterCommaSetting();
    public static CurrencySetting currencySetting = new CurrencySetting();
    public static NotificationSetting notificationSetting = new NotificationSetting();

    public static void writeSettings() {
        ObjectOutputStream out;
        try {
            out = new ObjectOutputStream(new FileOutputStream(FILE));
            out.writeObject(bankSetting);
            out.writeObject(digitsSetting);
            out.writeObject(currencySetting);
            out.writeObject(notificationSetting);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readSettings() {
        ObjectInputStream in;
        try {
            in = new ObjectInputStream(new FileInputStream(FILE));
            bankSetting = (BankSetting) in.readObject();
            digitsSetting = (NumberSimbolsAfterCommaSetting) in.readObject();
            currencySetting = (CurrencySetting) in.readObject();
            notificationSetting = (NotificationSetting) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
