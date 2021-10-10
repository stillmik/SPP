package math;

import timeTracer.TimeTracer;

public class MathOperations {

    private TimeTracer timeTracer;
    private A a;

    public MathOperations(TimeTracer tracer) {
        this.timeTracer = tracer;
        a = new A(timeTracer);
    }

    public void largeTree(){
        Thread thread0 = new Thread(() -> {

            microSleep();
            sleepLong();
            Thread thread1 = new Thread(this::sleepLong);
            thread1.start();
            B b = new B(timeTracer);
            b.sleep1000();
            try {
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread0.start();

        microSleep();
        Thread thread1 = new Thread(this::sleepLong);
        thread1.start();
        sleepLong();
        B b = new B(timeTracer);
        b.sleep1000();

        try {
            thread0.join();
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void mediumTree(){
        microSleep();
        Thread thread1 = new Thread(this::sleepLong);
        thread1.start();
        sleepLong();
        B b = new B(timeTracer);
        b.sleep1000();

        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void smallTree(){
        microSleep();
        sleepLong();
        B b = new B(timeTracer);
        b.sleep1000();
    }

    public TimeTracer getTimeTracer() {
        return timeTracer;
    }

    private void microSleep() {
        timeTracer.startTrace();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timeTracer.stopTrace();
    }

    private void sleepLong() {
        timeTracer.startTrace();
        try {
            Thread.sleep(1000);
            a.mathSleep1000();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timeTracer.stopTrace();
    }
}
