package banksAPIparsing;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data

public class Privatbank {
    private String ccy;
    private String base_ccy;
    private String buy;
    private String sale;
}
