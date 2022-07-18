package settings;

import java.util.HashMap;
import java.util.Map;

public class CurrencySetting {
    enum Currency{
        USD,
        EUR,
        RUB,
        UAH
    }


    private Map<Long,Currency> savedCurrency = new HashMap<>();


    public Currency getSavedCurrency(long chatId){
        return  savedCurrency.getOrDefault(chatId, Currency.USD);
    }

    public void setSavedCurrency(long chatId, Currency currency){
        savedCurrency.put(chatId,currency);
    }
}
