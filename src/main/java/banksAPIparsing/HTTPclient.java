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
    private static final List<Currency> allExchangeRates = new ArrayList<>();

    public static List<Currency> getAllExchangeRates() {
        return allExchangeRates;
    }

    public static void getAllBanksData() throws IOException, InterruptedException {
        var coursesPrivat = getPrivatbankData();
        var coursesMono = getMonobankData();
        var coursesNBU= getNBUData();

        coursesPrivat.ifPresent(currencyPair -> allExchangeRates.addAll(Arrays.asList(currencyPair)));
        coursesMono.ifPresent(currencyPair -> allExchangeRates.addAll(Arrays.asList(currencyPair)));
        coursesNBU.ifPresent(currencyPair -> allExchangeRates.addAll(Arrays.asList(currencyPair)));

        //allExchangeRates.forEach(x -> System.out.println(x.getCurrencyNumber() + ":" + x.getCurrencyCode()));
        allExchangeRates.forEach(x -> System.out.println(
                x.getBankName() + ": " +
                        x.getCurrencyNumber() + " - " +
                        x.getBuy()));
    }

    public static Optional<Privatbank[]> getPrivatbankData() throws IOException, InterruptedException {
        Optional<Privatbank[]> result = Optional.empty();
        try {
            return Optional.of(GSON.fromJson(
                    sendGETRequest("https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=4"),
                    Privatbank[].class));
        } catch (RuntimeException e) {
            return result;
        }
    }

    public static Optional<Monobank[]> getMonobankData() throws IOException, InterruptedException {
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

    public static Optional<NBU[]> getNBUData() throws IOException, InterruptedException {
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
