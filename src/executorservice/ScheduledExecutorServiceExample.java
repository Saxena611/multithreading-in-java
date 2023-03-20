package executorservice;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class StockMarketDataExtractor implements Runnable {

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        System.out.println("Downloading data from NSE portal....");
    }
}

public class ScheduledExecutorServiceExample {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        // Task is executed in parallel at a fixed rate in background.
        scheduledExecutorService.scheduleAtFixedRate(new StockMarketDataExtractor(), 1000, 2000,TimeUnit.MILLISECONDS);

        System.out.println("Execution came here.....Observe task is still executing in background.");
    }
}
