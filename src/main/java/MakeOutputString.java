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


    public String processInfo(Long chatId) {
        List<Currency> selectedCurrencys = CurrencySetting.getSavedCurrencies(chatId);

        NumberSimbolsAfterCommaSetting.NumberSimbolsAfterComma afterComma =
                NumberSimbolsAfterCommaSetting.getSimbolsAfterComma(chatId);

        Bank selectedBank = BankSetting.getSavedBank(chatId);

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
                            resp.getBank() == selectedBank) {
                        if (resp.getBank() == Bank.MONO) {
                            Monobank mono = (Monobank) resp;
                            if (!mono.getCurrencyCodeB().equals("980")) {
                                continue;
                            }
                        }
                        outputString += "Банк: " + resp.getBankName() + "\n";
                        outputString += "Валюта: " + resp.getCurrencyCode() + "\n";
                        if (selectedBank == Bank.NBU) {
                            outputString += "Офіційний курс: " + String.format(regular, resp.getBuy()) + "\n";
                            outputString += "\n";
                            break;
                        }
                        outputString += "Продаж: " + String.format(regular, resp.getSell()) + "\n";
                        outputString += "Купівля: " + String.format(regular, resp.getBuy()) + "\n";
                        outputString += "\n";
                    }
                }
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return  outputString;
    }
}