package math;

import timeTracer.TimeTracer;

class A {

    private TimeTracer timeTracer;
    private C c;

    A(TimeTracer timeTracer){
        this.timeTracer = timeTracer;
        c =new C(timeTracer);
    }

    private void mathSleep500(){
        timeTracer.startTrace();
        c.mathSleep500();
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
