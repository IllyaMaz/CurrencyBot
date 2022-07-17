package settings;

import java.util.HashMap;
import java.util.Map;

public class BankSetting {
    private Map<Long,Bank> originalBank = new HashMap<>();

    public void setOriginalBank(long chatId,Bank bank){
        originalBank.put(chatId,bank);
    }

    public Bank getOriginalBank(Long chatId){
        return originalBank.getOrDefault(chatId,Bank.PRIVAT);
    }
}
