package banksAPIparsing;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data

public class NBU {
    private String r030;
    private String txt;
    private String rate;
    private String cc;
    private String exchangedate;
}
