import banksAPIparsing.*;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;


public class Main {
    public static void main(String[] args)  {
        try {
            var coursesPrivat = HTTPclient.getPrivatbankData();
            coursesPrivat.ifPresent(currencyPair -> Arrays.stream(currencyPair).map(HTTPclient.GSON::toJson).forEach(System.out::println));

            var coursesMono = HTTPclient.getMonobankData();
            coursesMono.ifPresent(currencyPair -> Arrays.stream(currencyPair).forEach(System.out::println));

            var coursesNBU= HTTPclient.getNBUData();
            coursesNBU.ifPresent(currencyPair -> Arrays.stream(currencyPair).forEach(System.out::println));


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
