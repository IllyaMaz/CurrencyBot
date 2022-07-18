import settings.DecimalPlaceSetting;
import java.text.DecimalFormat;

//передать message.chatId , Double курс_валюты
//вернет строку с числом с запятой выставленной в DecimalPlaceSetting
public interface DecimalFormatable {
    static String formatDecimalPlace(Long chatId, Double currencyPrice) {
        return new DecimalFormat(DecimalPlaceSetting
                .getDecimalPlace(chatId)
                .getPattern()).format(currencyPrice);
    }
}
