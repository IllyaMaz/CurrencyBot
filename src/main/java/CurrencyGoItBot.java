import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class CurrencyGoItBot extends TelegramLongPollingBot {


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
    public void onUpdateReceived(Update update)  {
        if(update.hasMessage()) {
            Message message = update.getMessage();
            try {
                new SendMessage();
                execute(SendMessage.builder().chatId(message.getChatId().toString()).text("No func.realisation yet").build());
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }
}
