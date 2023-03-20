package executorservice;

class Task implements Runnable {

    public long taskId;

    public Task(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        taskId += (long) Math.random() * 5;
        System.out.println("Task-id under execution " + taskId + " Thread name:" + Thread.currentThread().getId());
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}