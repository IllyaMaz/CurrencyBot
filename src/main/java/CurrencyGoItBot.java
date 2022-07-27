import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
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
        String textCommand = update.getMessage().getText();
        switch (textCommand) {
            case "/start":
                execute(SendMessage.builder()
                        .chatId(message.getChatId().toString())
                        .text("Ласкаво просимо. Наш бот допоможе відслідковувати актуальні курси валют.")
                        .replyMarkup(Button.getInitialButtons())
                        .build());
                break;
        }
    }

    private void handleCallback(CallbackQuery callbackQuery) throws TelegramApiException {
        System.out.println();
        Message message = callbackQuery.getMessage();
        String data = callbackQuery.getData();
        Long chatId = message.getChatId();
        switch (data) {
            case "buttonGetInfo":
                MakeOutputString makeOutputString = new MakeOutputString();
                String output = makeOutputString.processInfo(chatId);
                execute(SendMessage.builder()
                        .chatId(chatId.toString())
                        .text(output)
                        .replyMarkup(Button.getInitialButtons())
                        .build());
                break;
            case "buttonSettings":
                execute(SendMessage.builder()
                        .chatId(chatId.toString())
                        .text("Налаштування.")
                        .replyMarkup(Button.getSettingsButtons())
                        .build());
                break;
            case "buttonDigitsNumber":
                execute(SendMessage.builder()
                        .chatId(chatId.toString())
                        .text("Виберіть кількість знаків після коми")
                        .replyMarkup(NumberSimbolsAfterCommaSetting.getDigitsButtons(chatId))
                        .build());
                break;
            case "buttonBank":
                execute(SendMessage.builder()
                        .chatId(chatId.toString())
                        .text("Виберіть банк")
                        .replyMarkup(BankSetting.getBankButtons(chatId))
                        .build());
                break;
            case "buttonCurrencies":
                execute(SendMessage.builder()
                        .chatId(chatId.toString())
                        .text("Виберіть валюту")
                        .replyMarkup(CurrencySetting.getCurrenciesButtons(chatId))
                        .build());
                break;
            case "buttonNotificationTime":
                execute(SendMessage.builder()
                        .chatId(chatId.toString())
                        .text("Виберіть час сповіщення")
                        .replyMarkup(Button.getNotificationButtons())
                        .build());
                break;

            case "NBU":
            case "PRIVAT":
            case "MONO":
                bankSetting.setSavedBank(chatId, BankSetting.Bank.valueOf(callbackQuery.getData()));

                execute(EditMessageReplyMarkup.builder()
                        .chatId(chatId)
                        .messageId(message.getMessageId())
                        .replyMarkup(BankSetting.getBankButtons(chatId))
                        .build());
                execute(SendMessage.builder()
                        .chatId(message.getChatId().toString())
                        .text("Банк обрано")
                        .replyMarkup(Button.getReturnButton())
                        .build());
                break;

            case "TWO":
            case "THREE":
            case "FOUR":
                digitsSetting.setSimbolsAfterComma(chatId, NumberSimbolsAfterCommaSetting.NumberSimbolsAfterComma
                        .valueOf(callbackQuery.getData()));

                execute(EditMessageReplyMarkup.builder()
                        .chatId(chatId)
                        .messageId(message.getMessageId())
                        .replyMarkup(NumberSimbolsAfterCommaSetting.getDigitsButtons(chatId))
                        .build());
                execute(SendMessage.builder()
                        .chatId(message.getChatId().toString())
                        .text("Кількість десяткових розрядів збережено")
                        .replyMarkup(Button.getReturnButton())
                        .build());
                break;

            case "USD":
            case "EUR":
            case "GBP":
                currencySetting.setSavedCurrency(chatId, CurrencySetting.Currency.valueOf(callbackQuery.getData()));

                execute(EditMessageReplyMarkup.builder()
                        .chatId(chatId)
                        .messageId(message.getMessageId())
                        .replyMarkup(CurrencySetting.getCurrenciesButtons(chatId))
                        .build());
                execute(SendMessage.builder()
                        .chatId(message.getChatId().toString())
                        .text("Валюту обрано")
                        .replyMarkup(Button.getReturnButton())
                        .build());
                break;
        }
    }

    private void handleMessage(Message message) throws TelegramApiException {
        String text = message.getText();
        switch (text) {
            case "9":
                execute(SendMessage.builder()
                        .chatId(message.getChatId().toString())
                        .text("Надішлемо Вам сповіщення о 9 годині.")
                        .build());
                NotificationSetting.setNotification(message.getChatId(), NotificationSetting.Notification.NINE);
                break;
            case "10":
                execute(SendMessage.builder()
                        .chatId(message.getChatId().toString())
                        .text("Надішлемо Вам сповіщення о 10 годині.")
                        .build());
                NotificationSetting.setNotification(message.getChatId(), NotificationSetting.Notification.TEN);
                break;
            case "11":
                execute(SendMessage.builder()
                        .chatId(message.getChatId().toString())
                        .text("Надішлемо Вам сповіщення о 11 годині.")
                        .build());
                NotificationSetting.setNotification(message.getChatId(), NotificationSetting.Notification.ELEVEN);
                break;
            case "12":
                execute(SendMessage.builder()
                        .chatId(message.getChatId().toString())
                        .text("Надішлемо Вам сповіщення о 12 годині.")
                        .build());
                NotificationSetting.setNotification(message.getChatId(), NotificationSetting.Notification.TWELVE);
                break;
            case "13":
                execute(SendMessage.builder()
                        .chatId(message.getChatId().toString())
                        .text("Надішлемо Вам сповіщення о 13 годині.")
                        .build());
                NotificationSetting.setNotification(message.getChatId(), NotificationSetting.Notification.THIRTEEN);
                break;
            case "14":
                execute(SendMessage.builder()
                        .chatId(message.getChatId().toString())
                        .text("Надішлемо Вам сповіщення о 14 годині.")
                        .build());
                NotificationSetting.setNotification(message.getChatId(), NotificationSetting.Notification.FOURTEEN);
                break;
            case "15":
                execute(SendMessage.builder()
                        .chatId(message.getChatId().toString())
                        .text("Надішлемо Вам сповіщення о 15 годині.")
                        .build());
                NotificationSetting.setNotification(message.getChatId(), NotificationSetting.Notification.FIFTEEN);
                break;
            case "16":
                execute(SendMessage.builder()
                        .chatId(message.getChatId().toString())
                        .text("Надішлемо Вам сповіщення о 16 годині.")
                        .build());
                NotificationSetting.setNotification(message.getChatId(), NotificationSetting.Notification.SIXTEEN);
                break;
            case "17":
                execute(SendMessage.builder()
                        .chatId(message.getChatId().toString())
                        .text("Надішлемо Вам сповіщення о 17 годині.")
                        .build());
                NotificationSetting.setNotification(message.getChatId(), NotificationSetting.Notification.SEVENTEEN);
                break;
            case "18":
                execute(SendMessage.builder()
                        .chatId(message.getChatId().toString())
                        .text("Надішлемо Вам сповіщення о 18 годині.")
                        .build());
                NotificationSetting.setNotification(message.getChatId(), NotificationSetting.Notification.EIGHTEEN);
                break;
            case "Вимкнути сповіщення":
                execute(SendMessage.builder()
                        .chatId(message.getChatId().toString())
                        .text("Ви вимкнули сповіщення.")
                        .build());
                NotificationSetting.setNotification(message.getChatId(), NotificationSetting.Notification.OFF_NOTIFY);
                break;
        }
    }

    public void sendNotification(long chatId) throws TelegramApiException {
        MakeOutputString makeOutputString = new MakeOutputString();
        execute(SendMessage.builder()
                .chatId(String.valueOf(chatId))
                .text(makeOutputString.processInfo(chatId))
                .replyMarkup(Button.getInitialButtons())
                .build());
    }
}
