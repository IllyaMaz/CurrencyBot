package banksAPIparsing;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

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
        if (Objects.nonNull(rateBuy)) return Double.parseDouble(rateBuy);
        else return 0.0;
    }

    @Override
    public double getSell() {
        if (Objects.nonNull(rateSell)) return Double.parseDouble(rateSell);
        else return 0.0;
    }
}
