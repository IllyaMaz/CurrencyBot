import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import settings.NotificationSetting;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Notification implements Runnable {
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private CurrencyGoItBot bot;
    private Map<Long, NotificationSetting.Notification> settings;
    private long delay;

    public Notification(Map<Long, NotificationSetting.Notification> settings, CurrencyGoItBot bot) {
        System.out.println("Start notification thread...");
        this.settings = settings;
        this.bot = bot;
    }

    @Override
    @SneakyThrows
    public void run() {
        while (true) {
            if (LocalTime.now().isBefore(LocalTime.of(9, 0))) {
                delay = ChronoUnit.MILLIS.between(
                        LocalTime.now(),
                        LocalTime.of(9, 0));
            } else if (LocalTime.now().isAfter(LocalTime.of(18, 0))) {
                delay = ChronoUnit.MILLIS.between(LocalTime.now(), LocalTime.MAX) +
                        ChronoUnit.MILLIS.between(LocalTime.MIDNIGHT, LocalTime.of(9,0));
            } else {
                delay = ChronoUnit.MILLIS.between(
                        LocalTime.now(),
                        LocalTime.of(LocalTime.now().getHour() + 1, 0, 0));
            }

            scheduler.schedule(() -> {
                settings.entrySet().stream()
                        .filter(x -> x.getValue().getTime() == LocalTime.now().getHour())
                        .forEach(x -> {
                            System.out.println("Message sent ID: " + x.getKey());
                            try {
                                bot.sendNotification(x.getKey());
                            } catch (TelegramApiException e) {
                                throw new RuntimeException(e);
                            }
                        });
            }, delay, TimeUnit.MILLISECONDS);

            Thread.sleep(delay + 1000 * 60 * 5);

        }
    }
}
