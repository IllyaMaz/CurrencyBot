package banksAPIparsing;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data

public class Monobank implements Currency{
    private String currencyCodeA;
    private String currencyCodeB;
    private String date;
    private String rateSell;
    private String rateBuy;
    private String rateCross;

    @Override
    public String getBankName() {
        return "Monobank";
    }

    @Override
    public int getCurrencyNumber() {
        return Integer.parseInt(currencyCodeA);
    }

    @Override
    public String getCurrencyCode() {
        var entry = Currencies.currs
                .entrySet().stream()
                .filter(x -> x.getValue() == Integer.parseInt(currencyCodeA))
                .findAny();
        return entry.isPresent() ? entry.get().getKey() : "Code not found";
    }

    @Override
    public double getBuy() {
        return Double.parseDouble(rateBuy);
    }

    @Override
    public double getSell() {
        return Double.parseDouble(rateSell);
    }
}
