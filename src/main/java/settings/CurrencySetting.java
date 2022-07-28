package settings;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static settings.CurrencySetting.Currency.USD;

public class CurrencySetting implements Externalizable {
    @Override
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(savedCurrencies);
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        savedCurrencies = (Map<Long, List<Currency>>) objectInput.readObject();
    }

    public enum Currency {
        USD,
        EUR,
        GBP
    }


    private static Map<Long, List<Currency>> savedCurrencies = new HashMap<>();

    public void setSavedCurrency(long chatId, Currency currency) {
        if (savedCurrencies.containsKey(chatId)) {
            List<Currency> savedList = savedCurrencies.get(chatId);
            if (savedList.contains(currency)) {
                savedList.remove(currency);
            } else {
                savedList.add(currency);
            }
        } else {
            List<Currency> savedList = new ArrayList<>();
            savedList.add(currency);
            savedCurrencies.put(chatId, savedList);
        }
    }

    public static List<Currency> getSavedCurrencies(long chatId){
        if (savedCurrencies.containsKey(chatId)) {
            return savedCurrencies.get(chatId);
        } else {
            List<Currency> savedList = new ArrayList<>();
            savedList.add(USD);
            return savedList;
        }
    }

    String setButtonUSDName(Long chatId) {
        return  (getSavedCurrencies(chatId).contains(CurrencySetting.Currency.USD)) ? "USD" + " ✅" : "USD" ;
    }

    String setButtonEURName(Long chatId) {
        return  (getSavedCurrencies(chatId).contains(CurrencySetting.Currency.EUR)) ? "EUR" + " ✅" : "EUR" ;
    }

    String setButtonGBPName(Long chatId) {
        return  (getSavedCurrencies(chatId).contains(CurrencySetting.Currency.GBP)) ? "GBP" + " ✅" : "GBP" ;
    }

    public static InlineKeyboardMarkup getCurrenciesButtons(Long chatId) {
        InlineKeyboardMarkup currenciesMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton buttonUSD = new InlineKeyboardButton();
        buttonUSD.setText(new CurrencySetting().setButtonUSDName(chatId));
        buttonUSD.setCallbackData("USD");
        InlineKeyboardButton buttonEUR = new InlineKeyboardButton();
        buttonEUR.setText(new CurrencySetting().setButtonEURName(chatId));
        buttonEUR.setCallbackData("EUR");
        InlineKeyboardButton buttonGBP = new InlineKeyboardButton();
        buttonGBP.setText(new CurrencySetting().setButtonGBPName(chatId));
        buttonGBP.setCallbackData("GBP");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(buttonUSD);
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow2.add(buttonEUR);
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        keyboardButtonsRow3.add(buttonGBP);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);

        currenciesMarkup.setKeyboard(rowList);

        return currenciesMarkup;
    }
}

