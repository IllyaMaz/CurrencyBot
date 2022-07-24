package banksAPIparsing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import settings.BankSetting;

@AllArgsConstructor
@Data
@EqualsAndHashCode

public class Privatbank implements BankResponse {
    private String ccy;
    private String base_ccy;
    @EqualsAndHashCode.Exclude private double buy;
    @EqualsAndHashCode.Exclude private double sale;

    @Override
    public String getBankName() {
        return "Privatbank";
    }

    @Override
    public int getCurrencyNumber() {
        try {
            return Currencies.currs.get(ccy);
        } catch (NullPointerException e) {
            return 0;
        }
    }

    @Override
    public String getCurrencyCode() {
        return ccy;
    }

    @Override
    public double getSell() {
        return sale;
    }

    @Override
    public BankSetting.Bank getBank() {
        return BankSetting.Bank.PRIVAT;
    }
}
