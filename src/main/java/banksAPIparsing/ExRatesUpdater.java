package banksAPIparsing;

import lombok.Data;

import java.io.IOException;

@Data

public class ExRatesUpdater implements Runnable{
    private long sleepMinutes = 3;

    @Override
    public void run() {
        try {
            for(;;) {
                System.out.println("Updating exchange rates..");
                HTTPclient.updateAllExchangeRates();
                Thread.sleep(sleepMinutes * 60 * 1000);
            }
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
