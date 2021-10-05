package math;

import timeTracer.TimeTracer;

class C {
    private TimeTracer timeTracer;

    C(TimeTracer timeTracer){
        this.timeTracer = timeTracer;
    }

    void mathSleep500(){
        timeTracer.startTrace();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timeTracer.stopTrace();
    }
}
