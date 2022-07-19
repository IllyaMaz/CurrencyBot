package settings;

import java.util.HashMap;
import java.util.Map;

public class CurrencySetting {
    public enum Currency{
        USD(840),
        EUR(978),
        RUB(643),
        UAH(980),
        GBP(826);
        private final int currencyCode;

        Currency(int currencyCode) {
            this.currencyCode = currencyCode;
        }

        public int getCurrencyCode() {
            return currencyCode;
        }
    }

    private static final Currency ORIGINAL_CURRENCY = Currency.UAH;
    private static final Map<Long,Currency> targetCurrency = new HashMap<>();

    public Currency getORIGINAL_CURRENCY(){
        return ORIGINAL_CURRENCY;
    }

    public static Currency getTargetCurrency(long chatId){
        return  targetCurrency.getOrDefault(chatId,Currency.USD);
    }

    public void setTargetCurrency(long chatId, Currency currency){
        targetCurrency.put(chatId,currency);
    }
}
