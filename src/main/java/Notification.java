import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.time.LocalTime;
import java.time.temporal.ChronoField;

public class Notification implements Runnable {
    public static long notificationSleepTime(int timeInHour) {
        LocalTime now = LocalTime.now();
        int milliSecInSec = 1000;
        long milliSecDayNow = (long) now.get(ChronoField.SECOND_OF_DAY) * milliSecInSec;
        long milliSecInDay = (long) timeInHour * 60 * 60 * milliSecInSec;

        if (milliSecDayNow != milliSecInDay) {
            return milliSecInDay - milliSecDayNow;
        } else {
            return milliSecInDay;
        }
    }

    @Override
    public void run() {
/*        execute(SendMessage.builder()
                .chatId(chatId.toString())
                .text("Я надішлю Вам сповіщення о " + 12 + " годині.") // вставить число, которое вставили
                .build());
        try {
            Thread.sleep(notificationSleepTime(12)); // вставить число, которое выбрали
            execute(SendMessage.builder()
                    .chatId(chatId.toString())
                    .text("Я надішлю Вам сповіщення о " + 12 + " годині.") // заменить на вывод ИНФО
                    .build());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
