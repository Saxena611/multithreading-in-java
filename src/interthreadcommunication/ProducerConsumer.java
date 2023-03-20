package interthreadcommunication;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumer {

    private List<Integer> list = new ArrayList<>();
    private static final int UPPER_LIMIT = 5;
    private static final int LOWER_LIMIT = 0;
    private final Object lock = new Object();
    private int value = 0;

    public void producer() throws InterruptedException {
        synchronized (lock) {
           while (true) {
               if (list.size() == UPPER_LIMIT) {
                   System.out.println("Waiting for removal of items!");
                   lock.wait();
               } else {
                   System.out.println("Adding value: "+ value);
                   list.add(value);
                   value++;
                   /**
                    * We can call the notify - because the other thread will be notified.
                    * when it is in the waiting state.
                    * */
                   lock.notify();
               }
               Thread.sleep(500);
           }
        }
    }

    public void consumer() throws InterruptedException {

        synchronized (lock) {
            while (true) {

                if (list.size() == LOWER_LIMIT) {
                    System.out.println("Waiting for adding items");
                    lock.wait();
                } else {
                    System.out.println("Removing value:" + list.remove(list.size() - 1));
                    /**
                     * We can call the notify - because the other thread will be notified.
                     * when it is in the waiting state.
                     * */
                    lock.notify();
                }
                Thread.sleep(500);

            }
        }

    }

    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer();
        Thread t1 = new Thread(() -> {
            try {
                pc.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                pc.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
    }

}
