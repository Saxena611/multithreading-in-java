package executorservice;

import java.util.concurrent.Callable;

public class CallableExample implements Callable<String> {
    private int id;

    public CallableExample(int id) {
        this.id = id;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public String call() throws Exception {
        Thread.sleep(20000);
        return "Id: " + this.id;
    }
}
