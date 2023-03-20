package executorservice;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolExample {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // 100 tasks distributed among 10 threads, which are executed parallel.
        for (int i = 1; i <= 100; i++)
            executorService.execute(new Task(i));

        executorService.shutdown();
    }
}
