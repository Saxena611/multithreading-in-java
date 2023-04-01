package concurrentlibrary;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class FirstWorkerConcurrentMap implements Runnable {

    private ConcurrentMap<String, Integer> map;

    public FirstWorkerConcurrentMap(ConcurrentMap<String, Integer> map) {
        this.map = map;
    }

    @Override
    public void run() {
        try {
            map.put("B", 1);
            map.put("H", 2);
            map.put("F", 3);
            Thread.sleep(1000);
            map.put("A", 4);
            Thread.sleep(1000);
            map.put("E", 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class SecondWorkerConcurrentMap implements Runnable {

    private ConcurrentMap<String, Integer> map;

    public SecondWorkerConcurrentMap(ConcurrentMap<String, Integer> map) {
        this.map = map;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println(map.get("A"));
            Thread.sleep(1000);
            System.out.println(map.get("E"));
            Thread.sleep(1000);
            System.out.println(map.get("C"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ConcurrentMaps {

    public static void main(String[] args) {

        ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();

        FirstWorkerConcurrentMap firstWorker = new FirstWorkerConcurrentMap(map);
        SecondWorkerConcurrentMap secondWorker = new SecondWorkerConcurrentMap(map);

        new Thread(firstWorker).start();
        new Thread(secondWorker).start();

    }
}
