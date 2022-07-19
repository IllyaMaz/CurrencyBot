import settings.NumberSimbolsAfterCommaSetting;
import java.text.DecimalFormat;

//передать message.chatId , Double курс_валюты
//вернет строку с числом с запятой выставленной в DecimalPlaceSetting
public interface DecimalFormatable {
    static String formatDecimalPlace(long chatId, Double currencyPrice) {
        return new DecimalFormat(NumberSimbolsAfterCommaSetting
                .getSimbolsAfterComma(chatId)
                .getPattern())
                    .format(currencyPrice);
    }
}