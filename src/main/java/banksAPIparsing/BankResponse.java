package banksAPIparsing;

import settings.BankSetting;

public interface BankResponse {
    String getBankName();
    int getCurrencyNumber();
    String getCurrencyCode();
    double getBuy();
    double getSell();
    BankSetting.Bank getBank();
}
