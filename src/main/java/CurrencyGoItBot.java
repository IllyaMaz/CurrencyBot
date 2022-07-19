import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import settings.CurrencySetting;
import settings.DecimalPlaceSetting;

import java.util.Optional;

public class CurrencyGoItBot extends TelegramLongPollingBot {
    private static final DecimalPlaceSetting DECIMAL_PLACE_SETTING = new DecimalPlaceSetting();
    private static final CurrencySetting CURRENCY_SETTING = new CurrencySetting();

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

   /* private void handleCallback(CallbackQuery callbackQuery) throws TelegramApiException {
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
                        .replyMarkup(Button.getDigitsButtons(message.getChatId()))
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
            case "button2":
                DECIMAL_PLACE_SETTING.setDecimalPlace(message.getChatId(), DecimalPlaceSetting.DecimalPlace.TWO);
                refreshDecimalPointButtons(message);
                break;
            case "button3":
                DECIMAL_PLACE_SETTING.setDecimalPlace(message.getChatId(), DecimalPlaceSetting.DecimalPlace.THREE);
                refreshDecimalPointButtons(message);
                break;
            case "button4":
                DECIMAL_PLACE_SETTING.setDecimalPlace(message.getChatId(), DecimalPlaceSetting.DecimalPlace.FOUR);
                refreshDecimalPointButtons(message);
                break;
        }
    }*/


    private void handleCallback(CallbackQuery callbackQuery) throws TelegramApiException {
        Message message = callbackQuery.getMessage();
        Long chatId = message.getChatId();
        String data = callbackQuery.getData();
        switch (data) {
            case "buttonSettings" -> buildMessage(message, "Налаштування.", Button.getSettingsButtons());
            case "buttonDigitsNumber" -> buildMessage(message, "Виберіть кількість знаків після коми", Button.getDigitsButtons(chatId));
            case "buttonBank" -> buildMessage(message, "Виберіть банк", Button.getBankButtons());
            case "buttonCurrencies" -> buildMessage(message, "Виберіть валюту", Button.getCurrenciesButtons(chatId));
            case "buttonNotificationTime" -> buildMessage(message, "Виберіть час сповіщення", Button.getNotificationButtons());
            case "button2" -> {
                DECIMAL_PLACE_SETTING.setDecimalPlace(chatId, DecimalPlaceSetting.DecimalPlace.TWO);
                refreshMessage(message, Button.getDigitsButtons(chatId));
            }
            case "button3" -> {
                DECIMAL_PLACE_SETTING.setDecimalPlace(chatId, DecimalPlaceSetting.DecimalPlace.THREE);
                refreshMessage(message, Button.getDigitsButtons(chatId));
            }
            case "button4" -> {
                DECIMAL_PLACE_SETTING.setDecimalPlace(chatId, DecimalPlaceSetting.DecimalPlace.FOUR);
                refreshMessage(message, Button.getDigitsButtons(chatId));
            }
            case "buttonUSD" -> {
                CURRENCY_SETTING.setTargetCurrency(chatId, CurrencySetting.Currency.USD);
                refreshMessage(message, Button.getCurrenciesButtons(chatId));
            }
            case "buttonEUR" -> {
                CURRENCY_SETTING.setTargetCurrency(chatId, CurrencySetting.Currency.EUR);
                refreshMessage(message, Button.getCurrenciesButtons(chatId));
            }
            case "buttonRUB" -> {
                CURRENCY_SETTING.setTargetCurrency(chatId, CurrencySetting.Currency.RUB);
                refreshMessage(message, Button.getCurrenciesButtons(chatId));
            }
        }
    }

    private void buildMessage(Message message, String text, ReplyKeyboard replyKeyboard) throws TelegramApiException {
        execute(SendMessage.builder()
                .chatId(message.getChatId().toString())
                .text(text)
                .replyMarkup(replyKeyboard)
                .build());
    }

    private void refreshMessage(Message message, InlineKeyboardMarkup inlineKeyboardMarkup) throws TelegramApiException {
        execute(EditMessageReplyMarkup.builder()
                .chatId(message.getChatId())
                .messageId(message.getMessageId())
                .replyMarkup(inlineKeyboardMarkup)
                .build());
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