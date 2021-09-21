
class InnerClass {

    private TimeTracer timeTracer;

    InnerClass(TimeTracer timeTracer){
        this.timeTracer = timeTracer;
    }

    void doubleIncrement(Integer[] arr){
        timeTracer.startTrace();
        for (int i=0;i<arr.length;i++){
            arr[i]=arr[i]+1;
        }
        increment(arr);
        timeTracer.stopTrace();
        System.out.println(timeTracer.getTraceResult().getTraceResult());
    }

    private void increment(Integer[] arr){
        timeTracer.startTrace();
        for (int i=0;i<arr.length;i++){
            arr[i]=arr[i]+1;
        }
        timeTracer.stopTrace();
        System.out.println(timeTracer.getTraceResult().getTraceResult());
    }

    private void mathSleep500(){
        timeTracer.startTrace();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timeTracer.stopTrace();
        System.out.println(timeTracer.getTraceResult().getTraceResult());
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
        System.out.println(timeTracer.getTraceResult().getTraceResult());
    }

}
