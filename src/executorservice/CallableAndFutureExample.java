package executorservice;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CallableAndFutureExample {


    public static void main(String[] args) {
        ExecutorService executorService =
                Executors.newFixedThreadPool(10);
        List<Future<String>> futureList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<String> future =
                    executorService.submit(new CallableExample(i));
            futureList.add(future);
        }

        for (Future<String> f : futureList
        ) {
            try {
                System.out.println(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            e.printStackTrace();
        }
    }
}
