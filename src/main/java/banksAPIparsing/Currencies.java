package banksAPIparsing;

import java.util.HashMap;
import java.util.Map;

public class Currencies {
    public static Map<String, Integer> currs = new HashMap<String, Integer>();

    static {
        currs.put("USD",840);
        currs.put("EUR",978);
        currs.put("CAD",1);
        currs.put("CZK",1);
        currs.put("GBP",1);
        currs.put("ILS",1);
        currs.put("JPY",1);
        currs.put("NOK",1);
        currs.put("PLZ",1);
        currs.put("SEK",1);

        currs.put("BTC",1);
    }

}
