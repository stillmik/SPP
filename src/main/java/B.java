import timeTracer.TimeTracer;
import timeTracer.TreeTimeTracer;

class B {

    private TimeTracer timeTracer;

    B(TimeTracer timeTracer){
        this.timeTracer = timeTracer;
    }

    private void sleep500(){
        timeTracer.startTrace();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timeTracer.stopTrace();
    }

    void sleep1000(){
        timeTracer.startTrace();
        try {
            Thread.sleep(500);
            sleep500();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timeTracer.stopTrace();
    }
}
