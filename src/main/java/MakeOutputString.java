import banksAPIparsing.*;
import banksAPIparsing.BankResponse;
import settings.BankSetting.*;
import settings.CurrencySetting;
import settings.CurrencySetting.*;
import settings.NumberSimbolsAfterCommaSetting;

import static settings.CurrencySetting.getSavedCurrencies;
import java.io.IOException;
import java.util.List;



public class MakeOutputString {
    private String outputString = "";
    private static Long chatId;
    public Long getChatId() {
        return chatId;
    }
    public void setChatId(Long chatId) {
        MakeOutputString.chatId = chatId;
    }
    public static void main(String[] args) {
        MakeOutputString mos = new MakeOutputString();
        mos.processInfo();
    }

    public void processInfo() {
        CurrencySetting currencySetting = new CurrencySetting();
        currencySetting.setSavedCurrency(1L,
                CurrencySetting.Currency.valueOf("EUR"));
        currencySetting.setSavedCurrency(1L,
                CurrencySetting.Currency.valueOf("USD"));





        setChatId(1L);
        List<Currency> selectedCurrencys = CurrencySetting.getSavedCurrencies(chatId);
        System.out.println("Выбранные валюты: (List<Currency>) " + selectedCurrencys);
        NumberSimbolsAfterCommaSetting.NumberSimbolsAfterComma afterComma =
                NumberSimbolsAfterCommaSetting.getSimbolsAfterComma(chatId);
        System.out.println("Выбранное колличество знаков после запятой: " + afterComma);
        String regular = "";

        switch (afterComma){
            case TWO:
                regular = "%.2f";
                break;
            case THREE:
                regular = "%.3f";
                break;
            case FOUR:
                regular = "%.4f";
                break;
        }
        List<BankResponse> list;
        try {
            list = HTTPclient.getAllExchangeRates();

            for (BankResponse resp: list) {
                for (Currency currency : selectedCurrencys) {
                    if (resp.getCurrencyCode().equalsIgnoreCase(currency.name())) {
                        System.out.println();
                        outputString += "Банк: " + resp.getBankName() + "\n";
                        outputString += "Валюта: " + resp.getCurrencyCode() + "\n";
                        outputString += "Продажа: " + String.format(regular, resp.getSell()) + "\n";
                        outputString += "Покупка: " + String.format(regular, resp.getBuy()) + "\n";
                        outputString += "\n";
                        System.out.println();
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("outputString = " + "\n\n" + outputString);
    }

}
