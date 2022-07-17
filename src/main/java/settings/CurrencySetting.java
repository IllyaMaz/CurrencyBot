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

    private Currency originalCurrency = Currency.UAH;
    private Map<Long,Currency> targetCurrency = new HashMap<>();

    public Currency getOriginalCurrency(){
        return originalCurrency;
    }

    public Currency getTargetCurrency(long chatId){
        return  targetCurrency.getOrDefault(chatId,Currency.USD);
    }

    public void setTargetCurrency(long chatId, Currency currency){
        targetCurrency.put(chatId,currency);
    }
}
