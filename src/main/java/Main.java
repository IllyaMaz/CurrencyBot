import banksAPIparsing.ExRatesUpdater;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import settings.*;

public class Main {
    public static void main(String[] args) {
        new Thread(new ExRatesUpdater()).start();
        CurrencyGoItBot bot = new CurrencyGoItBot(new DefaultBotOptions());
        TelegramBotsApi botsApi;
        try {
            botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        new Thread(new Notification(NotificationSetting.getNotificationMap(), bot)).start();
    }
}
