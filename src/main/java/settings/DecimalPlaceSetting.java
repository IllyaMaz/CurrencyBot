package settings;

import java.util.HashMap;
import java.util.Map;

public class DecimalPlaceSetting {
    enum DecimalPlace {
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

    private Map<Long,DecimalPlace> decimalPlaceMap = new HashMap<>();

    public void setSimbolsAfterComma(long chatId, DecimalPlace decimalPlace){
        decimalPlaceMap.put(chatId,decimalPlace);
    }

    public DecimalPlace getSimbolsAfterComma(long chatId){
        return decimalPlaceMap.getOrDefault(chatId, DecimalPlace.TWO);
    }

}
