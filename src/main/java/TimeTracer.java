import java.util.Stack;

public class TimeTracer implements Tracer {

    private Stack<Long> startTimeStack = new Stack<>();

    private long processingTime;

    @Override
    public void startTrace() {
        processingTime=0;
        startTimeStack.push(System.currentTimeMillis());
    }

    @Override
    public void stopTrace() {
        processingTime = System.currentTimeMillis() - startTimeStack.pop();
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
