package settings;

import java.util.HashMap;
import java.util.Map;

public class DecimalPlaceSetting {
    public enum DecimalPlace {
        TWO(2,"#0.00"),
        THREE(3,"#0.000"),
        FOUR(4,"#0.0000");
        private int position;
        private String pattern;

        DecimalPlace(int position, String pattern) {
            this.position = position;
            this.pattern = pattern;
        }

        public int getPosition() {
            return position;
        }

        public String getPattern() {
            return pattern;
        }
    }

    private static final Map<Long,DecimalPlace> decimalPlaceMap = new HashMap<>();

    public static void setDecimalPlace(long chatId, DecimalPlace decimalPlace){
        decimalPlaceMap.put(chatId,decimalPlace);
    }

    public static DecimalPlace getDecimalPlace(long chatId){
        return decimalPlaceMap.getOrDefault(chatId, DecimalPlace.TWO);
    }

}
