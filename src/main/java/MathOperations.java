import timeTracer.TimeTracer;
import timeTracer.TraceResult;

class MathOperations {

    private TimeTracer timeTracer = new TimeTracer();
    private A a =new A(timeTracer);

    MathOperations(){
        sleep();
        sleepLong();
        B b = new B(timeTracer);
        new Thread(){
            public  void  run(){
                b.sleep1000();
            }
        }.start();
        b.sleep1000();
        TraceResult traceResult = timeTracer.getTraceResult();
        traceResult.getTraceResult();
    }

    private void sleep(){
        timeTracer.startTrace();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timeTracer.stopTrace();
    }

    private void sleepLong(){
        timeTracer.startTrace();
        try {
            Thread.sleep(500);
            a.mathSleep1000();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timeTracer.stopTrace();
    }
}
