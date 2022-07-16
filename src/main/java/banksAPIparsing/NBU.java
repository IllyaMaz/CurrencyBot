package banksAPIparsing;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data

public class NBU {
    private String startDate;
    private String TimeSign;
    private String CurrencyCode;
    private String CurrencyCodeL;
    private String Units;
    private String Amount;
}
