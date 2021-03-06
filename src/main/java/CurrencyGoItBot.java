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
                        .text("?????????????? ??????????????. ?????? ?????? ???????????????? ?????????????????????????????? ?????????????????? ?????????? ??????????.")
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
                        .text("????????????????????????.")
                        .replyMarkup(Button.getSettingsButtons())
                        .build());
                break;
            case "buttonDigitsNumber":
                execute(SendMessage.builder()
                        .chatId(chatId.toString())
                        .text("???????????????? ?????????????????? ???????????? ?????????? ????????")
                        .replyMarkup(NumberSimbolsAfterCommaSetting.getDigitsButtons(chatId))
                        .build());
                break;
            case "buttonBank":
                execute(SendMessage.builder()
                        .chatId(chatId.toString())
                        .text("???????????????? ????????")
                        .replyMarkup(BankSetting.getBankButtons(chatId))
                        .build());
                break;
            case "buttonCurrencies":
                execute(SendMessage.builder()
                        .chatId(chatId.toString())
                        .text("???????????????? ????????????")
                        .replyMarkup(CurrencySetting.getCurrenciesButtons(chatId))
                        .build());
                break;
            case "buttonNotificationTime":
                execute(SendMessage.builder()
                        .chatId(chatId.toString())
                        .text("???????????????? ?????? ????????????????????")
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
        String text = message.getText();
        switch (text) {
            case "9":
                execute(SendMessage.builder()
                        .chatId(message.getChatId().toString())
                        .text("???????????????????? ???????????????? ?? 9 ????????.")
                        .build());
        }
    }
}