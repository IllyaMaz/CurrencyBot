import banksAPIparsing.*;
import banksAPIparsing.BankResponse;

import settings.*;
import settings.BankSetting.*;
import settings.CurrencySetting;
import settings.CurrencySetting.*;

import settings.NumberSimbolsAfterCommaSetting;

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
        //setChatId(1L);
//****************************************************************************************
//        CurrencySetting currencySetting = new CurrencySetting();
//        currencySetting.setSavedCurrency(1L, Currency.valueOf("EUR"));
//        currencySetting.setSavedCurrency(1L, Currency.valueOf("USD"));
//
//        NumberSimbolsAfterCommaSetting numberSimbolsAfterCommaSetting
//                = new NumberSimbolsAfterCommaSetting();
//        numberSimbolsAfterCommaSetting
//                .setSimbolsAfterComma(1L, NumberSimbolsAfterCommaSetting
//                        .NumberSimbolsAfterComma
//                        .valueOf("FOUR"));
//
//        BankSetting bankSetting = new BankSetting();
//        bankSetting.setSavedBank(1L, Bank.MONO);
//****************************************************************************************
        List<Currency> selectedCurrencys = CurrencySetting.getSavedCurrencies(chatId);
        System.out.println("Выбранные валюты: " + selectedCurrencys);
        NumberSimbolsAfterCommaSetting.NumberSimbolsAfterComma afterComma =
                NumberSimbolsAfterCommaSetting.getSimbolsAfterComma(chatId);
        System.out.println("Выбранное колличество знаков после запятой: " + afterComma);
        Bank selectedBank = BankSetting.getSavedBank(chatId);
        System.out.println("Выбранный банк: " + selectedBank.name().toLowerCase());
        System.out.println();


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
                    if (resp.getCurrencyCode().equalsIgnoreCase(currency.name()) &&
                        resp.getBankName().toLowerCase().contains(selectedBank.name().toLowerCase())) {
                        outputString += "Банк: " + resp.getBankName() + "\n";
                        outputString += "Валюта: " + resp.getCurrencyCode() + "\n";
                        outputString += "Продажа: " + String.format(regular, resp.getSell()) + "\n";
                        outputString += "Покупка: " + String.format(regular, resp.getBuy()) + "\n";
                        outputString += "\n";
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(outputString);
    }
}
