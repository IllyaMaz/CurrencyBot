import settings.NumberSimbolsAfterCommaSetting;
import java.text.DecimalFormat;

public interface DecimalFormatable {
    static String formatDecimalPlace(long chatId, Double currencyPrice) {
        return new DecimalFormat(NumberSimbolsAfterCommaSetting
                .getSimbolsAfterComma(chatId)
                .getPattern())
                    .format(currencyPrice);
    }
}