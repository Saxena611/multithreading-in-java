package interthreadcommunication;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExample {


    public static AtomicInteger counter = new AtomicInteger(0);
    public static int inconsistentCounter = 0;

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                increment();
                incrementToSeeInconsistency();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                increment();
                incrementToSeeInconsistency();
            }
        });

        t1.start();
        t2.start();
        try {
            t1.join(); // wait for thread to finish.
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // AtomicInteger basically jave.util.concurrent is specifically designed to handle multithreading operations.
        System.out.println(counter.get());
        System.out.println(inconsistentCounter); // The value should be 20,000 but it will not be generated.
    }

    public static void increment() {
        for (int i = 0; i < 10000; i++)
            counter.getAndIncrement();
    }

    public static void incrementToSeeInconsistency() {
        for (int i = 0; i < 10000; i++)
            inconsistentCounter++;
    }

}
