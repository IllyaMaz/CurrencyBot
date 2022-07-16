package banksAPIparsing;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data

public class Monobank {
    private String currencyCodeA;
    private String currencyCodeB;
    private String date;
    private String rateSell;
    private String rateBuy;
    private String rateCross;
}
