package math;

import timeTracer.TimeTracer;
import timeTracer.TraceResult;

public class MathOperations {

    private TimeTracer timeTracer = new TimeTracer();
    private A a = new A(timeTracer);

    public MathOperations() {
        Thread thread0 = new Thread(() -> {

            microSleep();
            sleepLong();
            B b = new B(timeTracer);
            b.sleep1000();
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
        TraceResult traceResult = timeTracer.getTraceResult();
        traceResult.getJSON();
        traceResult.getXML();
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
