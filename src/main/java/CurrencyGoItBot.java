import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import settings.BankSetting;
import settings.CurrencySetting;
import settings.NumberSimbolsAfterCommaSetting;

import java.text.DecimalFormat;
import java.util.Optional;

public class CurrencyGoItBot extends TelegramLongPollingBot implements DecimalFormatable {
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
        } else if (update.hasMessage() && update.getMessage().hasEntities()) {
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

    private void handleCallback(CallbackQuery callbackQuery)  throws TelegramApiException {
        System.out.println();
        Message message = callbackQuery.getMessage();
        String data = callbackQuery.getData();
        Long chatId = message.getChatId();
        switch (data) {
            case "buttonGetInfo":
                MakeOutputString makeOutputString = new MakeOutputString();
                makeOutputString.setChatId(chatId);
                makeOutputString.processInfo();
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

                break;

            case "TWO":
            case "THREE":
            case "FOUR":
                digitsSetting.setSimbolsAfterComma(chatId, NumberSimbolsAfterCommaSetting.NumberSimbolsAfterComma.valueOf(callbackQuery.getData()));

                execute(EditMessageReplyMarkup.builder()
                        .chatId(chatId)
                        .messageId(message.getMessageId())
                        .replyMarkup(NumberSimbolsAfterCommaSetting.getDigitsButtons(chatId))
                        .build());

                break;

            case "USD":
            case "EUR":
            case "RUB":
                currencySetting.setSavedCurrency(chatId, CurrencySetting.Currency.valueOf(callbackQuery.getData()));

                execute(EditMessageReplyMarkup.builder()
                        .chatId(chatId)
                        .messageId(message.getMessageId())
                        .replyMarkup(CurrencySetting.getCurrenciesButtons(chatId))
                        .build());

                break;

        }

    }

    private void handleMessage(Message message) throws TelegramApiException {

        if (message.hasText() && message.hasEntities()) {
            Optional<MessageEntity> commandEntities = message.getEntities()
                    .stream()
                    .filter(e -> "bot_command".equals(e.getType())).findFirst();
            if (((Optional<?>) commandEntities).isPresent()) {
                String command = message
                        .getText()
                        .substring(commandEntities.get().getOffset(), commandEntities.get().getLength());
                switch (command) {
                    case "some text":
                        break;
                    case "9":
                        Notification.notificationSleepTime(9);
                        execute(SendMessage.builder()
                                .chatId(message.getChatId())
                                .text("Я надішлю Вам сповіщення о 9 годині.")
                                .build());
                        break;
                    case "10":
                        Notification.notificationSleepTime(10);
                        execute(SendMessage.builder()
                                .chatId(message.getChatId())
                                .text("Я надішлю Вам сповіщення о 10 годині.")
                                .build());
                        break;
                    case "11":
                        Notification.notificationSleepTime(11);
                        execute(SendMessage.builder()
                                .chatId(message.getChatId())
                                .text("Я надішлю Вам сповіщення о 11 годині.")
                                .build());
                        break;
                    case "12":
                        Notification.notificationSleepTime(12);
                        execute(SendMessage.builder()
                                .chatId(message.getChatId())
                                .text("Я надішлю Вам сповіщення о 12 годині.")
                                .build());
                        break;
                    case "13":
                        Notification.notificationSleepTime(13);
                        execute(SendMessage.builder()
                                .chatId(message.getChatId())
                                .text("Я надішлю Вам сповіщення о 13 годині.")
                                .build());
                        break;
                    case "14":
                        Notification.notificationSleepTime(14);
                        execute(SendMessage.builder()
                                .chatId(message.getChatId())
                                .text("Я надішлю Вам сповіщення о 14 годині.")
                                .build());
                        break;
                    case "15":
                        Notification.notificationSleepTime(15);
                        execute(SendMessage.builder()
                                .chatId(message.getChatId())
                                .text("Я надішлю Вам сповіщення о 15 годині.")
                                .build());
                        break;
                    case "16":
                        Notification.notificationSleepTime(16);
                        execute(SendMessage.builder()
                                .chatId(message.getChatId())
                                .text("Я надішлю Вам сповіщення о 16 годині.")
                                .build());
                        break;
                    case "17":
                        Notification.notificationSleepTime(17);
                        execute(SendMessage.builder()
                                .chatId(message.getChatId())
                                .text("Я надішлю Вам сповіщення о 17 годині.")
                                .build());
                        break;
                    case "18":
                        Notification.notificationSleepTime(18);
                        execute(SendMessage.builder()
                                .chatId(message.getChatId())
                                .text("Я надішлю Вам сповіщення о 18 годині.")
                                .build());
                        break;
                    case "-1":
                        execute(SendMessage.builder()
                                .chatId(message.getChatId())
                                .text("Сповіщення вимкнені.")
                                .build());
                        Notification.notificationSleepTime(-1);
                        break;
                }
            }
        }
    }
}