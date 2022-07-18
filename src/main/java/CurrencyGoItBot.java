import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import settings.DecimalPlaceSetting;

import java.util.Optional;

public class CurrencyGoItBot extends TelegramLongPollingBot {
    DecimalPlaceSetting decimalPlaceSetting = new DecimalPlaceSetting();
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

    private void handleCallback(CallbackQuery callbackQuery) throws TelegramApiException {
        Message message = callbackQuery.getMessage();
        String data = callbackQuery.getData();
        switch (data) {
            case "buttonSettings":
                execute(SendMessage.builder()
                        .chatId(message.getChatId().toString())
                        .text("Налаштування.")
                        .replyMarkup(Button.getSettingsButtons())
                        .build());
                break;
            case "buttonDigitsNumber":
                execute(SendMessage.builder()
                        .chatId(message.getChatId().toString())
                        .text("Виберіть кількість знаків після коми")
                        .replyMarkup(Button.getDigitsButtons())
                        .build());
                break;
            case "buttonBank":
                execute(SendMessage.builder()
                        .chatId(message.getChatId().toString())
                        .text("Виберіть банк")
                        .replyMarkup(Button.getBankButtons())
                        .build());
                break;
            case "buttonCurrencies":
                execute(SendMessage.builder()
                        .chatId(message.getChatId().toString())
                        .text("Виберіть валюту")
                        .replyMarkup(Button.getCurrenciesButtons())
                        .build());
                break;
            case "buttonNotificationTime":
                execute(SendMessage.builder()
                        .chatId(message.getChatId().toString())
                        .text("Виберіть час сповіщення")
                        .replyMarkup(Button.getNotificationButtons())
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
                }

            }

        }
    }
}