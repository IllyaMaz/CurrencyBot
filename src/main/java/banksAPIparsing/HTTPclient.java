package banksAPIparsing;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class HTTPclient {
    private final static HttpClient CLIENT = HttpClient.newHttpClient();
    public final static Gson GSON = new Gson().newBuilder().setPrettyPrinting().create();
    private static final List<Currency> ALL_RATES = new ArrayList<>();

/**
     <B>Использование:</B><br>
        1) в вашем коде вызываем метод getAllExchangeRates() и получаем список,
           типизированный интерфейсом Currency, пример: <br><br>
         <B><I><font color="#e3ff00"> List&#60Currency&#62 list = HTTPclient.getAllRates();</font></I></B><br><br>
        2) все необходимые методы уже вызываем непосредсвенно через доступные методы интерфейса Currency<br>

           или (если нужны специфические)
           кастуем к типу необходимого класса (Приватбанк, Монобанк, НБУ и тд)
 */

    public static List<Currency> getAllRates() throws IOException, InterruptedException {
        if (ALL_RATES.isEmpty()) getAllBanksData();
        return ALL_RATES;
    }

    private static void getAllBanksData() throws IOException, InterruptedException {
        addToStorage(getPrivatbankData());
        addToStorage(getMonobankData());
        addToStorage(getNBUData());
    }

    private static <T extends Currency> void addToStorage(Optional<T[]> courses) {
        courses.ifPresent(currencyPair -> Arrays.stream(currencyPair)
                .filter(currency -> Currencies.currs.containsKey(currency.getCurrencyCode()))
                .forEach(ALL_RATES::add));
    }

    private static Optional<Privatbank[]> getPrivatbankData() throws IOException, InterruptedException {
        Optional<Privatbank[]> result = Optional.empty();
        try {
            return Optional.of(GSON.fromJson(
                    sendGETRequest("https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=4"),
                    Privatbank[].class));
        } catch (RuntimeException e) {
            return result;
        }
    }


    private static Optional<Monobank[]> getMonobankData() throws IOException, InterruptedException {
        Optional<Monobank[]> result = Optional.empty();
        try {
            return Optional.of(GSON.fromJson(
                    sendGETRequest("https://api.monobank.ua/bank/currency"),
                    Monobank[].class));
        } catch (RuntimeException e) {
            System.out.println("\033[1;31m" + "Can't get Monobank data" + "\033[0m");
            return result;
        }
    }

    private static Optional<NBU[]> getNBUData() throws IOException, InterruptedException {
        Optional<NBU[]> result = Optional.empty();
        try {
            String HTMLBody = sendGETRequest("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json");
            return Optional.of(GSON.fromJson(HTMLBody,NBU[].class));
        } catch (RuntimeException e) {
            System.out.println("\033[1;31m" + "Can't get NBU data" + "\033[0m");
            return  result;
        }
    }

    private static String sendGETRequest(String path) throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(path))
                .GET()
                .headers("Content-Type", "application/json")
                .build();
        return CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }
}
