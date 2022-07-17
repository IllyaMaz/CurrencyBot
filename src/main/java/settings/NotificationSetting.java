package settings;

import com.sun.tools.javac.Main;

import java.util.HashMap;
import java.util.Map;

public class NotificationSetting {
    enum Notification {
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
        OFF(-1);

        private int time;

        Notification(int time) {
            this.time = time;
        }

        public int getTime() {
            return time;
        }
    }

    private Map<Long,Notification> notificationMap = new HashMap<>();

    public void setNotification(long chatId,Notification notification){
        notificationMap.put(chatId,notification);
    }

    public Notification getNotification(long chatId){
        return notificationMap.getOrDefault(chatId,Notification.OFF);
    }
}
