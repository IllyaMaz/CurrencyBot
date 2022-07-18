package settings;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankSetting {
    public enum Bank{
        MONO,
        PRIVAT,
        NBU
    }
    private static Map<Long,Bank> savedBank = new HashMap<>();

    public void setSavedBank(Long chatId,Bank bank){
        savedBank.put(chatId,bank);
    }

    public static Bank getSavedBank(Long chatId){
        return savedBank.getOrDefault(chatId,Bank.PRIVAT);
    }

    String setPrivatButtonName(Long chatId) {
        return  (getSavedBank(chatId).equals(Bank.PRIVAT)) ? "ПриватБанк" + " ✅" : "ПриватБанк" ;
    }

    String setNBUButtonName(Long chatId) {
        return  (getSavedBank(chatId).equals(Bank.NBU)) ? "НБУ" + " ✅" : "НБУ" ;
    }

    String setMonoButtonName(Long chatId) {
        return  (getSavedBank(chatId).equals(Bank.MONO)) ? "МоноБанк" + " ✅" : "МоноБанк" ;
    }

    public static InlineKeyboardMarkup getBankButtons(Long chatId) {
        InlineKeyboardMarkup bankMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton buttonNBU = new InlineKeyboardButton();
        buttonNBU.setText(new BankSetting().setNBUButtonName(chatId));
        buttonNBU.setCallbackData("NBU");
        InlineKeyboardButton buttonPrivat = new InlineKeyboardButton();
        buttonPrivat.setText(new BankSetting().setPrivatButtonName(chatId));
        buttonPrivat.setCallbackData("PRIVAT");
        InlineKeyboardButton buttonMono = new InlineKeyboardButton();
        buttonMono.setText(new BankSetting().setMonoButtonName(chatId));
        buttonMono.setCallbackData("MONO");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(buttonNBU);
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow2.add(buttonPrivat);
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        keyboardButtonsRow3.add(buttonMono);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);

        bankMarkup.setKeyboard(rowList);

        return bankMarkup;
    }

}
