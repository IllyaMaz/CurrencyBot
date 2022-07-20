import banksAPIparsing.*;
import banksAPIparsing.BankResponse;
import settings.*;
import settings.BankSetting.*;
import settings.CurrencySetting;

import java.io.IOException;
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

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    private static Long chatId;

    public static void main(String[] args) {
        List<BankResponse> list;
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
                .map(BankResponse::getBuy).forEach(System.out::println);
    }

    public void processInfo() {
        Bank currBank = BankSetting.getSavedBank(chatId);
        List<CurrencySetting.Currency> currency = CurrencySetting.getSavedCurrencies(chatId);
        NumberSimbolsAfterCommaSetting.NumberSimbolsAfterComma afterComma =
                NumberSimbolsAfterCommaSetting.getSimbolsAfterComma(chatId);
        System.out.println("CurrBank: " + currBank.toString());
    }
}
