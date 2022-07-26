package settings;

import java.util.HashMap;
import java.util.Map;

public class NotificationSetting {

    public enum Notification {
        NINE(9),
        TEN(10),
        ELEVEN(11),
        TWELVE(12),
        THIRTEEN(13),
        FOURTEEN(14),
        FIFTEEN(15),
        SIXTEEN(16),
        SEVENTEEN(17),
        EIGHTEEN(18),
        OFF_NOTIFY(-1);

        private int time;

        Notification(int time) {
            this.time = time;
        }

        public int getTime() {
            return time;
        }
    }

    private static Map<Long, Notification> notificationMap = new HashMap<>();

    public static Map<Long, Notification> getNotificationMap() {
        return notificationMap;
    }

    public static void setNotification(long chatId, Notification notification) {
        notificationMap.put(chatId, notification);
    }

    public Notification getNotification(long chatId) {
        return notificationMap.getOrDefault(chatId, Notification.OFF_NOTIFY);
    }
}
