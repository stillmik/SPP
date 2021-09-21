
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
    }

    private void increment(Integer[] arr){
        timeTracer.startTrace();
        for (int i=0;i<arr.length;i++){
            arr[i]=arr[i]+1;
        }
        timeTracer.stopTrace();
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
