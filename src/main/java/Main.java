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
            List<Currency> list = new ArrayList<>();

            var coursesPrivat = HTTPclient.getPrivatbankData();
            //coursesPrivat.ifPresent(currencyPair -> Arrays.stream(currencyPair).forEach(System.out::println));
            coursesPrivat.ifPresent(currencyPair -> list.addAll(Arrays.asList(currencyPair)));

            var coursesMono = HTTPclient.getMonobankData();
            //coursesMono.ifPresent(currencyPair -> Arrays.stream(currencyPair).forEach(System.out::println));
            coursesMono.ifPresent(currencyPair -> list.addAll(Arrays.asList(currencyPair)));

            var coursesNBU= HTTPclient.getNBUData();
            //coursesNBU.ifPresent(currencyPair -> Arrays.stream(currencyPair).forEach(System.out::println));
            coursesNBU.ifPresent(currencyPair -> list.addAll(Arrays.asList(currencyPair)));

            list.forEach(x -> System.out.println(x.getCurrencyNumber() + ":" + x.getCurrencyCode()));




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
