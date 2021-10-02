import timeTracer.TimeTracer;
import timeTracer.TreeTimeTracer;

class A {

    private TimeTracer timeTracer;

    A(TimeTracer timeTracer){
        this.timeTracer = timeTracer;
    }

    private void mathSleep500(){
        timeTracer.startTrace();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timeTracer.stopTrace();
    }

    void mathSleep1000(){
        timeTracer.startTrace();
        try {
            Thread.sleep(500);
            mathSleep500();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timeTracer.stopTrace();
    }
}
