package settings;

import java.util.HashMap;
import java.util.Map;

public class NumberSimbolsAfterCommaSetting {
    enum NumberSimbolsAfterComma {
        TWO,
        THREE,
        FOUR
    }

    private Map<Long,NumberSimbolsAfterComma> simbolsAfterCommaMap = new HashMap<>();

    public void setSimbolsAfterComma(long chatId, NumberSimbolsAfterComma numberSimbolsAfterComma){
        simbolsAfterCommaMap.put(chatId,numberSimbolsAfterComma);
    }

    public NumberSimbolsAfterComma getSimbolsAfterComma(long chatId){
        return simbolsAfterCommaMap.getOrDefault(chatId, NumberSimbolsAfterComma.TWO);
    }

}
