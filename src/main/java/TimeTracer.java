
public class TimeTracer implements Tracer {

    private long startTime;
    private long stopTime;
    private long processingTime;

    @Override
    public void startTrace() {
        startTime=stopTime=processingTime=0;
        startTime = System.currentTimeMillis();
    }

    @Override
    public void stopTrace() {
        stopTime = System.currentTimeMillis();
        processingTime = stopTime - startTime;
    }

    @Override
    public TraceResult getTraceResult() {
        return new TraceResult(getMethodName(),processingTime,getClassName());
    }

    private String getMethodName(){
        return Thread.currentThread().getStackTrace()[3].getMethodName();
    }

    private String getClassName(){
        return Thread.currentThread().getStackTrace()[3].getClassName();
    }
}
