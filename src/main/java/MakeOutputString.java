import banksAPIparsing.*;
import banksAPIparsing.Currency;
import settings.*;
import settings.BankSetting.*;
import settings.CurrencySetting.*;

import java.io.IOException;
import java.net.http.HttpClient;
import java.util.List;

public class MakeOutputString {

//    class Output() {
//        String
//    }

    private String outputString = "";
    private Bank bank;
    private String currency;
    private String sell;
    private String buye;

    public static void main(String[] args) {
        List<Currency> list;
        try {
            list = HTTPclient.getAllExchangeRates();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        for(Currency currency : list) {
//            System.out.println("\n"
//                    + "Банк : " + currency.getBankName() + "\n"
//                    + "Валюта : " + currency.getCurrencyCode() + "\n"
//                    + "Продажа : " + currency.getBuy() + "\n"
//                    + "Покупка : " + currency.getSell() + "\n"
//            );
//        }

        list.stream().filter(x -> x.getCurrencyCode().equals("USD"))
                .map(Currency::getBuy).forEach(System.out::println);


        BankSetting.getSavedBank(123);
    }
}
