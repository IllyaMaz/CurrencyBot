package banksAPIparsing;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data

public class Privatbank implements BankResponse {
    private String ccy;
    private String base_ccy;
    private double buy;
    private double sale;

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
}
