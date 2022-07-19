package settings;

import java.util.HashMap;
import java.util.Map;

public class DecimalPlaceSetting {
    public enum DecimalPlace {
        TWO("2","#0.00"),
        THREE("3","#0.000"),
        FOUR("4","#0.0000");
        private final String position;
        private final String pattern;

        DecimalPlace(String position, String pattern) {
            this.position = position;
            this.pattern = pattern;
        }

        public String getPosition() {
            return position;
        }

        public String getPattern() {
            return pattern;
        }
    }

    private static final Map<Long,DecimalPlace> decimalPlaceMap = new HashMap<>();

    public void setDecimalPlace(long chatId, DecimalPlace decimalPlace){
        decimalPlaceMap.put(chatId,decimalPlace);
    }

    public static DecimalPlace getDecimalPlace(long chatId){
        return decimalPlaceMap.getOrDefault(chatId, DecimalPlace.TWO);
    }

}
