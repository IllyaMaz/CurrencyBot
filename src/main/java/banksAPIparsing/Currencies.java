package banksAPIparsing;

import java.util.HashMap;
import java.util.Map;

public class Currencies {
    public static Map<String, Integer> currs = new HashMap<String, Integer>();

    static {
        currs.put("USD",840);
        currs.put("EUR",978);
        currs.put("CAD",null);
        currs.put("CZK",null);
        currs.put("GBP",null);
        currs.put("ILS",null);
        currs.put("JPY",null);
        currs.put("NOK",null);
        currs.put("PLZ",null);
        currs.put("SEK",null);

        currs.put("BTC",null);
    }

}
