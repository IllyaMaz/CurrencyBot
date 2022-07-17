package settings;

import java.util.HashMap;
import java.util.Map;

public class NumberSimbolsAfterCommaSetting {
    private Map<Long,NumberSimbolsAfterComma> simbolsAfterCommaMap = new HashMap<>();

    public void setSimbolsAfterComma(long chayId, NumberSimbolsAfterComma numberSimbolsAfterComma){
        simbolsAfterCommaMap.put(chayId,numberSimbolsAfterComma);
    }

    public NumberSimbolsAfterComma getSimbolsAfterComma(long chatId){
        return simbolsAfterCommaMap.getOrDefault(chatId, NumberSimbolsAfterComma.TWO);
    }

}
