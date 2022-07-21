import banksAPIparsing.*;
import banksAPIparsing.BankResponse;
import settings.BankSetting.*;
import settings.CurrencySetting;
import settings.CurrencySetting.*;
import settings.NumberSimbolsAfterCommaSetting;

import static settings.CurrencySetting.getSavedCurrencies;
import java.io.IOException;
import java.util.List;



public class MakeOutputString {
    private String outputString = "";
    private Bank bank;
    private String currency;
    private String sell;
    private String buy;
    private static Long chatId;
    public Long getChatId() {
        return chatId;
    }
    public void setChatId(Long chatId) {
        MakeOutputString.chatId = chatId;
    }

    public static void main(String[] args) {
        MakeOutputString mos = new MakeOutputString();
        mos.processInfo();
//        setChatId(1L);

//        List<BankResponse> list;
//        try {
//            list = HTTPclient.getAllExchangeRates();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

//        for(BankResponse bankResponse : list) {
//            System.out.println("\n"
//                    + "Банк : " + bankResponse.getBankName() + "\n"
//                    + "Валюта : " + bankResponse.getCurrencyCode() + "\n"
//                    + "Продажа : " + bankResponse.getBuy() + "\n"
//                    + "Покупка : " + bankResponse.getSell() + "\n"
//            );
//        }

//        list.stream().filter(x -> x.getCurrencyCode().equals("USD"))
//                .map(BankResponse::getBuy).forEach(System.out::println);
    }

    public void processInfo() {
//*****************************************************************************
        setChatId(1L);

        List<Currency> selectedCurrencys = CurrencySetting.getSavedCurrencies(chatId);
        System.out.println("Выбранные валюты: " + selectedCurrencys);

        NumberSimbolsAfterCommaSetting.NumberSimbolsAfterComma afterComma =
                NumberSimbolsAfterCommaSetting.getSimbolsAfterComma(chatId);
        System.out.println("Выбранное колличество знаков после запятой: " + afterComma);



//*******************************************************************************


//        System.out.println("Выбранная валюта = "
//                            + CurrencySetting.getSavedCurrencies());

//        Monobank monobank = new Monobank();
//        NBU nbu = new NBU();
//        Privatbank privatbank = new Privatbank();
        //************************************************
        List<BankResponse> list;
        try {

            list = HTTPclient.getAllExchangeRates();

            for (BankResponse resp: list) {
//                if (resp.getBankName().equals("Monobank"))
//                System.out.println("resp.getCurrencyCode() = " + resp.getCurrencyCode());
//                System.out.println("CurrencySetting.Currency.EUR.toString() = " + CurrencySetting.Currency.EUR.toString());
//                if (resp.getCurrencyCode().equals(CurrencySetting.Currency.EUR.name()))
//                {
//                    for (Currency currency : selectedCurrencys) {
//                        if (resp.getCurrencyCode().equals(currency.toString())) {
                        System.out.println("**********************************************");
                        System.out.println("** resp.getBankName() = " + resp.getBankName());
                        System.out.println("** resp.getCurrencyCode() = " + resp.getCurrencyCode());
                        System.out.println("** resp.getSell() = " + resp.getSell());
                        System.out.println("** resp.getBuy() = " + resp.getBuy());
//                        } else {
//                            System.out.println("Такой валюты нет!!!");
//                        }
//                    }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //*****************************************
//        List<BankResponse> list;
//        for (BankResponse resp : list) {
//            System.out.println("resp.getBankName() = " + resp.getBankName());
//        }
////        System.out.println("CurrBank: " + currBank);
//
//        System.out.println("********************* Bank.PRIVAT = " + Bank.PRIVAT);
//
//        try {
//            list = HTTPclient.getAllExchangeRates();
//
//            for (BankResponse response : list) {
////                System.out.println("response.getBankName() = " + response.getBankName());
//                String bankNameLower = response.getBankName().toLowerCase();
////                if (bankNameLower.equals(Bank.MONO.name().toLowerCase()))
////                if (response.getBankName().equals(Monobank.))
//                {
//                    System.out.println("resp = " + response.getBankName());
//                }
//            }
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//************************ Получаем выбранные пользователем валюты *********************
//        System.out.println("Выбранные валюты: "
//                + CurrencySetting.getSavedCurrencies(chatId));
//        System.out.println("CurrencySetting.getSavedCurrencies(chatId) = "
//                );
//        List<Currency> currency = getSavedCurrencies(chatId);
//        for (CurrencySetting.Currency cur:
//             currency) {
//            System.out.println("cur = " + cur);
//        }
//        System.out.println("Currency: " + currency);        // Знаки после запятой надо

//************************ Получаем количество символов после запятой ******************
//        NumberSimbolsAfterCommaSetting.NumberSimbolsAfterComma afterComma =
//                NumberSimbolsAfterCommaSetting.getSimbolsAfterComma(chatId);
//        System.out.println("Выбранное колличество знаков после запятой: "
//                + NumberSimbolsAfterCommaSetting.getSimbolsAfterComma(chatId));
//        System.out.println("CurrencySetting.getSavedCurrencies(chatId) = "
//                + NumberSimbolsAfterCommaSetting.getSimbolsAfterComma(chatId));
//************************ Получаем количество символов после запятой ******************

//        System.out.println("AfterComma: " + afterComma);


/*
        List<BankResponse> list;
        try {

            list = HTTPclient.getAllExchangeRates();

            for (BankResponse resp:
                 list) {
//                if (resp.getBankName().equals(currBank))
                {
                    System.out.println("resp = " + resp);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

*/
    }

}
