package banksAPIparsing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import settings.BankSetting;

import java.util.Objects;

@AllArgsConstructor
@Data
@EqualsAndHashCode

public class Monobank implements BankResponse {
    @EqualsAndHashCode.Include private String currencyCodeA;
    @EqualsAndHashCode.Include private String currencyCodeB;
    @EqualsAndHashCode.Exclude private String date;
    @EqualsAndHashCode.Exclude private String rateSell;
    @EqualsAndHashCode.Exclude private String rateBuy;
    @EqualsAndHashCode.Exclude private String rateCross;

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
        else return Double.parseDouble(rateCross);
    }

    @Override
    public double getSell() {
        if (Objects.nonNull(rateSell)) return Double.parseDouble(rateSell);
        else return Double.parseDouble(rateCross);
    }

    @Override
    public BankSetting.Bank getBank() {
        return BankSetting.Bank.MONO;
    }
}
