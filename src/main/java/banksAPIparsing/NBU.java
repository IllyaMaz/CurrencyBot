package banksAPIparsing;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data

public class NBU implements BankResponse {
    private String r030;
    private String txt;
    private String rate;
    private String cc;
    private String exchangedate;

    @Override
    public String getBankName() {
        return "National Bank of Ukraine";
    }

    @Override
    public int getCurrencyNumber() {
        return Integer.parseInt(r030);
    }

    @Override
    public String getCurrencyCode() {
        var entry = Currencies.currs
                .entrySet().stream()
                .filter(x -> x.getValue() == Integer.parseInt(r030))
                .findAny();
        return entry.isPresent() ? entry.get().getKey() : "Code not found";
    }

    @Override
    public double getBuy() {
        return Double.parseDouble(rate);
    }

    @Override
    public double getSell() {
        return 0.0;
    }
}
