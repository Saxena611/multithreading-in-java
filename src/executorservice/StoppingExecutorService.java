package executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class StoppingExecutorService {


    /**
     * https://www.educative.io/answers/how-to-shut-down-the-executor-service-in-java
     * <p>
     * Once the executor service is shut down, it won’t wait for the completion of the threads in it. Hence, one of the ways to wait for the completion of the
     * threads when the executor service is shut down is two-phase termination.
     * <p>
     * The steps involved in the two-phase termination are as follows:
     * <p>
     * 1. First shutdown() method is invoked. This prevents from new tasks being submitted to the pool.
     * 2. Then awaitTermination() method is invoked with a timeout of n seconds. This call will block until the pool terminates or the timeout occurs.
     * 3. Then shutdownNow() method is invoked to cancel any remaining tasks.
     * 4. Then awaitTermination() method is invoked again with n seconds.
     * <p>
     * Ideally, by the fourth step, the pool should terminate. In case the pool doesn’t terminate after the fourth step, then we must inspect what’s going wrong!
     */
    public static void main(String[] args) {
        final ExecutorService threadPool = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            threadPool.execute(() -> {
                if (threadPool.isShutdown()) {
                    System.out.println("Pool is terminating.");
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("WARN: Task interrupted");
                }
                System.out.println("Doing work.");
            });
        }

        threadPool.shutdown();
        try {
            if (!threadPool.awaitTermination(2, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
                if (!threadPool.awaitTermination(2, TimeUnit.SECONDS)) {
                    System.out.println("The pool did not terminate, Check whats going wrong....");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
