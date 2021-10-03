import timeTracer.TimeTracer;

class MathOperations {

    private TimeTracer timeTracer = new TimeTracer();
    private A a =new A(timeTracer);
    private B b =new B(timeTracer);

    MathOperations(){
        sleep();
        sleepLong();
        b.sleep1000();
        //timeTracer.getTraceResult().getTraceResult();
        timeTracer.print();
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
