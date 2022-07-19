package settings;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberSimbolsAfterCommaSetting {
    public enum NumberSimbolsAfterComma {
        TWO("#0.00"),
        THREE("#0.000"),
        FOUR("#0.0000");

        private final String pattern;

        NumberSimbolsAfterComma(String pattern) {
            this.pattern = pattern;
        }

        public String getPattern() {
            return pattern;
        }
    }

    private static final Map<Long,NumberSimbolsAfterComma> simbolsAfterCommaMap = new HashMap<>();



    public void setSimbolsAfterComma(long chatId, NumberSimbolsAfterComma numberSimbolsAfterComma){
        simbolsAfterCommaMap.put(chatId,numberSimbolsAfterComma);
    }

    public static NumberSimbolsAfterComma getSimbolsAfterComma(long chatId){
        return simbolsAfterCommaMap.getOrDefault(chatId, NumberSimbolsAfterComma.TWO);
    }

    String setButton2Name(Long chatId) {
        return  (getSimbolsAfterComma(chatId).equals(NumberSimbolsAfterComma.TWO)) ? "2" + " ✅" : "2" ;
    }

    String setButton3Name(Long chatId) {
        return  (getSimbolsAfterComma(chatId).equals(NumberSimbolsAfterComma.THREE)) ? "3" + " ✅" : "3" ;
    }

    String setButton4Name(Long chatId) {
        return  (getSimbolsAfterComma(chatId).equals(NumberSimbolsAfterComma.FOUR)) ? "4" + " ✅" : "4" ;
    }

    public static InlineKeyboardMarkup getDigitsButtons(Long chatId) {
        InlineKeyboardMarkup digitsMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText(new NumberSimbolsAfterCommaSetting().setButton2Name(chatId));
        button2.setCallbackData("TWO");
        InlineKeyboardButton button3 = new InlineKeyboardButton();
        button3.setText(new NumberSimbolsAfterCommaSetting().setButton3Name(chatId));
        button3.setCallbackData("THREE");
        InlineKeyboardButton button4 = new InlineKeyboardButton();
        button4.setText(new NumberSimbolsAfterCommaSetting().setButton4Name(chatId));
        button4.setCallbackData("FOUR");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(button2);
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow2.add(button3);
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        keyboardButtonsRow3.add(button4);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);

        digitsMarkup.setKeyboard(rowList);
        return digitsMarkup;
    }

}
