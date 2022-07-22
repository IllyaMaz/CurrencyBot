package banksAPIparsing;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

public class HTTPclient {
    private final static HttpClient CLIENT = HttpClient.newHttpClient();
    public final static Gson GSON = new Gson().newBuilder().setPrettyPrinting().create();
    private final static List<BankResponse> ALL_RATES = new CopyOnWriteArrayList<>();

    /**
     * <B>Использование:</B><br>
     * 1) в вашем коде вызываем метод getAllExchangeRates() и получаем список,
     * типизированный интерфейсом BankResponse, пример: <br><br>
     * <B><I><font color="#e3ff00"> List&#60BankResponse&#62 list = HTTPclient.getAllExchangeRates();</font></I></B><br><br>
     * 2) все необходимые методы уже вызываем непосредсвенно через доступные методы интерфейса BankResponse<br><br>
     * <font color="#e3ff00">getBank();<br>
     * getBankName()<br>
     * <i>getCurrencyNumber();</i><br>
     * getCurrencyCode();<br>
     * getBuy();<br>
     * getSell();</font><br><br>
     * или (если нужны специфические)
     * кастуем к типу необходимого класса (Приватбанк,Монобанк,НБУ и тд)
     */

    public static List<BankResponse> getAllExchangeRates() throws IOException, InterruptedException {
        if (ALL_RATES.isEmpty()) getAllBanksData();
        return ALL_RATES;
    }

    public static void updateAllExchangeRates() throws IOException, InterruptedException {
        ALL_RATES.clear();
        getAllBanksData();
    }

    private static void getAllBanksData() throws IOException, InterruptedException {
        addToStorage(getPrivatbankData(4));
        addToStorage(getPrivatbankData(3));
        addToStorage(getMonobankData());
        addToStorage(getNBUData());
    }

    private static <T extends BankResponse> void addToStorage(Optional<T[]> courses) {
        courses.ifPresent(currencyArray -> Arrays.stream(currencyArray)
                .filter(currency -> Currencies.currs.containsKey(currency.getCurrencyCode()))
                .forEach(ALL_RATES::add));
    }

    private static Optional<Privatbank[]> getPrivatbankData(int coursId) throws IOException, InterruptedException {
        Optional<Privatbank[]> result = Optional.empty();
        try {
            return Optional.of(GSON.fromJson(
                    sendGETRequest("https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=" + coursId),
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
            return Optional.of(GSON.fromJson(HTMLBody, NBU[].class));
        } catch (RuntimeException e) {
            System.out.println("\033[1;31m" + "Can't get NBU data" + "\033[0m");
            return result;
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
