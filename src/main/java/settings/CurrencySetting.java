package settings;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static settings.CurrencySetting.Currency.USD;

public class CurrencySetting {
    public enum Currency {
        USD,
        EUR,
        RUB
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
            List<Currency> savedList = savedCurrencies.get(chatId);
            return savedList;
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

    String setButtonRUBName(Long chatId) {
        return  (getSavedCurrencies(chatId).contains(CurrencySetting.Currency.RUB)) ? "RUB" + " ✅" : "RUB" ;
    }

    public static InlineKeyboardMarkup getCurrenciesButtons(Long chatId) {
        InlineKeyboardMarkup currenciesMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton buttonUSD = new InlineKeyboardButton();
        buttonUSD.setText(new CurrencySetting().setButtonUSDName(chatId));
        buttonUSD.setCallbackData("USD");
        InlineKeyboardButton buttonEUR = new InlineKeyboardButton();
        buttonEUR.setText(new CurrencySetting().setButtonEURName(chatId));
        buttonEUR.setCallbackData("EUR");
        InlineKeyboardButton buttonRUB = new InlineKeyboardButton();
        buttonRUB.setText(new CurrencySetting().setButtonRUBName(chatId));
        buttonRUB.setCallbackData("RUB");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(buttonUSD);
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow2.add(buttonEUR);
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        keyboardButtonsRow3.add(buttonRUB);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);

        currenciesMarkup.setKeyboard(rowList);

        return currenciesMarkup;
    }
}

