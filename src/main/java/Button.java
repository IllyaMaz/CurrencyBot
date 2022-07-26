import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class Button {


    static InlineKeyboardMarkup getInitialButtons() {
        InlineKeyboardMarkup initialMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton buttonGetInfo = new InlineKeyboardButton();
        buttonGetInfo.setText("Отримати інфо");
        buttonGetInfo.setCallbackData("buttonGetInfo");
        InlineKeyboardButton buttonSettings = new InlineKeyboardButton();
        buttonSettings.setText("Налаштування");
        buttonSettings.setCallbackData("buttonSettings");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow1.add(buttonGetInfo);
        keyboardButtonsRow2.add(buttonSettings);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        initialMarkup.setKeyboard(rowList);

        return initialMarkup;
    }

    static InlineKeyboardMarkup getSettingsButtons() {
        InlineKeyboardMarkup settingsMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton buttonDigitsNumber = new InlineKeyboardButton();
        buttonDigitsNumber.setText("Кількість знаків після коми");
        buttonDigitsNumber.setCallbackData("buttonDigitsNumber");
        InlineKeyboardButton buttonBank = new InlineKeyboardButton();
        buttonBank.setText("Банк");
        buttonBank.setCallbackData("buttonBank");
        InlineKeyboardButton buttonCurrencies = new InlineKeyboardButton();
        buttonCurrencies.setText("Валюти");
        buttonCurrencies.setCallbackData("buttonCurrencies");
        InlineKeyboardButton buttonNotificationTime = new InlineKeyboardButton();
        buttonNotificationTime.setText("Час сповіщень");
        buttonNotificationTime.setCallbackData("buttonNotificationTime");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(buttonDigitsNumber);
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow2.add(buttonBank);
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        keyboardButtonsRow3.add(buttonCurrencies);
        List<InlineKeyboardButton> keyboardButtonsRow4 = new ArrayList<>();
        keyboardButtonsRow4.add(buttonNotificationTime);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);
        rowList.add(keyboardButtonsRow4);
        settingsMarkup.setKeyboard(rowList);

        return settingsMarkup;
    }

    static ReplyKeyboardMarkup getNotificationButtons() {
        ReplyKeyboardMarkup notificationMarkup = new ReplyKeyboardMarkup();
        notificationMarkup.setResizeKeyboard(true);
        notificationMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("9"));
        keyboardFirstRow.add(new KeyboardButton("10"));
        keyboardFirstRow.add(new KeyboardButton("11"));

        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(new KeyboardButton("12"));
        keyboardSecondRow.add(new KeyboardButton("13"));
        keyboardSecondRow.add(new KeyboardButton("14"));

        KeyboardRow keyboardThirdRow = new KeyboardRow();
        keyboardThirdRow.add(new KeyboardButton("15"));
        keyboardThirdRow.add(new KeyboardButton("16"));
        keyboardThirdRow.add(new KeyboardButton("17"));

        KeyboardRow keyboardFourthRow = new KeyboardRow();
        keyboardFourthRow.add(new KeyboardButton("18"));
        keyboardFourthRow.add(new KeyboardButton("Вимкнути сповіщення"));

        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThirdRow);
        keyboard.add(keyboardFourthRow);

        notificationMarkup.setKeyboard(keyboard);


        return notificationMarkup;
    }

    static ReplyKeyboardMarkup getReturnButton() {
        ReplyKeyboardMarkup notificationMarkup = new ReplyKeyboardMarkup();
        notificationMarkup.setResizeKeyboard(true);
        notificationMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("Повернутися"));



        keyboard.add(keyboardFirstRow);


        notificationMarkup.setKeyboard(keyboard);


        return notificationMarkup;
    }

}







