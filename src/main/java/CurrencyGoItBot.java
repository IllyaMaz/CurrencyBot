import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import settings.*;

public class CurrencyGoItBot extends TelegramLongPollingBot {
    BankSetting bankSetting = new BankSetting();
    NumberSimbolsAfterCommaSetting digitsSetting = new NumberSimbolsAfterCommaSetting();
    CurrencySetting currencySetting = new CurrencySetting();

    protected CurrencyGoItBot(DefaultBotOptions options) {
        super(options);
    }

    @Override
    public String getBotUsername() {
        return "@CurrencyGoItBot";
    }

    @Override
    public String getBotToken() {
        return "5403460194:AAHy6snkFdJlGwXSrtmNDQlDYAQbf-fFaOo";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().isCommand()) {
            try {
                handleCommand(update);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (update.hasCallbackQuery()) {
            try {
                handleCallback(update.getCallbackQuery());
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (update.hasMessage() && update.getMessage().hasText()) {
            try {
                handleMessage(update.getMessage());
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }


    private void handleCommand(Update update) throws TelegramApiException {
        Message message = update.getMessage();
        Long chatId = message.getChatId();
        String textCommand = update.getMessage().getText();
        switch (textCommand) {
            case "/start":
                buildReplyMessage(chatId,Button.getInitialButtons(),"Ласкаво просимо. Наш бот допоможе відслідковувати актуальні курси валют.");
                break;
        }
    }

    private void handleCallback(CallbackQuery callbackQuery)  throws TelegramApiException {
        Message message = callbackQuery.getMessage();
        String data = callbackQuery.getData();
        Long chatId = message.getChatId();
        switch (data) {
            case "buttonGetInfo":
                MakeOutputString makeOutputString = new MakeOutputString();
                String output = makeOutputString.processInfo(chatId);
                buildReplyMessage(chatId,Button.getInitialButtons(),output);
                break;
            case "buttonSettings":
                buildReplyMessage(chatId,Button.getSettingsButtons(),"Налаштування.");
                break;
            case "buttonDigitsNumber":
                buildReplyMessage(chatId,NumberSimbolsAfterCommaSetting.getDigitsButtons(chatId),"Виберіть кількість знаків після коми");
                break;
            case "buttonBank":
                buildReplyMessage(chatId,BankSetting.getBankButtons(chatId),"Виберіть банк");
                break;
            case "buttonCurrencies":
                buildReplyMessage(chatId,CurrencySetting.getCurrenciesButtons(chatId),"Виберіть валюту");
                break;
            case "buttonNotificationTime":
                buildReplyMessage(chatId,Button.getNotificationButtons(),"Виберіть час сповіщення");
                break;

            case "NBU":
            case "PRIVAT":
            case "MONO":
                bankSetting.setSavedBank(chatId, BankSetting.Bank.valueOf(callbackQuery.getData()));
                editReplyMessage(message,BankSetting.getBankButtons(chatId));
                buildReplyMessage(chatId,Button.getReturnButton(),"Банк обрано");
                break;

            case "TWO":
            case "THREE":
            case "FOUR":
                digitsSetting.setSimbolsAfterComma(chatId, NumberSimbolsAfterCommaSetting.NumberSimbolsAfterComma
                        .valueOf(callbackQuery.getData()));
                editReplyMessage(message,NumberSimbolsAfterCommaSetting.getDigitsButtons(chatId));
                buildReplyMessage(chatId,Button.getReturnButton(),"Кількість десяткових розрядів збережено");
                break;

            case "USD":
            case "EUR":
            case "GBP":
                currencySetting.setSavedCurrency(chatId, CurrencySetting.Currency.valueOf(callbackQuery.getData()));
                editReplyMessage(message,CurrencySetting.getCurrenciesButtons(chatId));
                buildReplyMessage(chatId,Button.getReturnButton(),"Валюту обрано");
                break;
        }
    }

    private void handleMessage(Message message) throws TelegramApiException {
        String text = message.getText();
        Long chatId = message.getChatId();
        switch (text) {
            case "Повернутися":
                buildReplyMessage(chatId,Button.getInitialButtons(),"Оберіть бажану функцію.");
                break;
            case "9":
                buildMessage(chatId,"Надішлемо Вам сповіщення о 9 годині.");
                NotificationSetting.setNotification(message.getChatId(), NotificationSetting.Notification.NINE);
                break;
            case "10":
                buildMessage(chatId,"Надішлемо Вам сповіщення о 10 годині.");
                NotificationSetting.setNotification(message.getChatId(), NotificationSetting.Notification.TEN);
                break;
            case "11":
                buildMessage(chatId,"Надішлемо Вам сповіщення о 11 годині.");
                NotificationSetting.setNotification(message.getChatId(), NotificationSetting.Notification.ELEVEN);
                break;
            case "12":
                buildMessage(chatId,"Надішлемо Вам сповіщення о 12 годині.");
                NotificationSetting.setNotification(message.getChatId(), NotificationSetting.Notification.TWELVE);
                break;
            case "13":
                buildMessage(chatId,"Надішлемо Вам сповіщення о 13 годині.");
                NotificationSetting.setNotification(message.getChatId(), NotificationSetting.Notification.THIRTEEN);
                break;
            case "14":
                buildMessage(chatId,"Надішлемо Вам сповіщення о 14 годині.");
                NotificationSetting.setNotification(message.getChatId(), NotificationSetting.Notification.FOURTEEN);
                break;
            case "15":
                buildMessage(chatId,"Надішлемо Вам сповіщення о 15 годині.");
                NotificationSetting.setNotification(message.getChatId(), NotificationSetting.Notification.FIFTEEN);
                break;
            case "16":
                buildMessage(chatId,"Надішлемо Вам сповіщення о 16 годині.");
                NotificationSetting.setNotification(message.getChatId(), NotificationSetting.Notification.SIXTEEN);
                break;
            case "17":
                buildMessage(chatId,"Надішлемо Вам сповіщення о 17 годині.");
                NotificationSetting.setNotification(message.getChatId(), NotificationSetting.Notification.SEVENTEEN);
                break;
            case "18":
                buildMessage(chatId,"Надішлемо Вам сповіщення о 18годині.");
                NotificationSetting.setNotification(message.getChatId(), NotificationSetting.Notification.EIGHTEEN);
                break;
            case "Вимкнути сповіщення":
                buildMessage(chatId,"Ви вимкнули сповіщення.");
                NotificationSetting.setNotification(message.getChatId(), NotificationSetting.Notification.OFF_NOTIFY);
                break;
        }
    }

    public void sendNotification(long chatId) throws TelegramApiException {
        MakeOutputString makeOutputString = new MakeOutputString();
        buildReplyMessage(chatId,Button.getInitialButtons(),makeOutputString.processInfo(chatId));
    }

    //create new message only with text
    private void buildMessage(Long chatId, String text) throws TelegramApiException {
        execute(SendMessage.builder()
                .chatId(chatId.toString())
                .text(text)
                .build());
    }

    //create new message with text and buttons
    private void buildReplyMessage(Long chatId, ReplyKeyboard keyboard, String text) throws TelegramApiException {
        execute(SendMessage.builder()
                .chatId(chatId.toString())
                .text(text)
                .replyMarkup(keyboard)
                .build());
    }

    //refresh message on buttons
    private void editReplyMessage(Message message, InlineKeyboardMarkup keyboard) throws TelegramApiException {
        Long chatId = message.getChatId();
        execute(EditMessageReplyMarkup.builder()
                .chatId(chatId.toString())
                .messageId(message.getMessageId())
                .replyMarkup(keyboard)
                .build());
    }
}
