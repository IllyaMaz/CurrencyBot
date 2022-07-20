package banksAPIparsing;

public interface BankResponse {
    String getBankName();
    int getCurrencyNumber();
    String getCurrencyCode();
    double getBuy();
    double getSell();
}
