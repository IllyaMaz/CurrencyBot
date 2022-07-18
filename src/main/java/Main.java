import banksAPIparsing.*;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class Main {
    public static void main(String[] args)  {
        try {
            HTTPclient.getAllBanksData();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
/*        CurrencyGoItBot bot = new CurrencyGoItBot(new DefaultBotOptions());
        TelegramBotsApi botsApi = null;
        try {
            botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }*/
    }
}
