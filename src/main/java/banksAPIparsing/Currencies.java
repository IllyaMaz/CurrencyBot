package banksAPIparsing;

import java.util.HashMap;
import java.util.Map;

public class Currencies {
    public static Map<String, Integer> currs = new HashMap<>(5);

    static {
        currs.put("USD",840);
        currs.put("EUR",978);
        currs.put("RUB",643);
        currs.put("UAH",980);
        currs.put("GBP",826);
    }

}
