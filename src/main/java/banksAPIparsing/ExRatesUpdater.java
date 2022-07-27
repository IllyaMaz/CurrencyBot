package banksAPIparsing;

import lombok.Data;
import lombok.SneakyThrows;

import java.io.IOException;

@Data

public class ExRatesUpdater implements Runnable {
    private long sleepMinutes = 3;

    @Override
    @SneakyThrows
    public void run() {
        for (;;) {
            System.out.println("Updating exchange rates..");
            HTTPclient.updateAllExchangeRates();
            Thread.sleep(sleepMinutes * 60 * 1000);

        }
    }
}
