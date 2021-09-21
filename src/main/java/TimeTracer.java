import java.util.Stack;

public class TimeTracer implements Tracer {

    private Stack<Long> startTimeStack = new Stack<>();
    private TraceResult traceResult=new TraceResult();

    private long processingTime;

    @Override
    public void startTrace() {
        processingTime=0;
        startTimeStack.push(System.currentTimeMillis());
    }

    @Override
    public void stopTrace() {
        processingTime = System.currentTimeMillis() - startTimeStack.pop();
        traceResult.addToResult(getMethodName(),processingTime,getClassName());
;    }

    @Override
    public TraceResult getTraceResult() {
        return  traceResult;
    }

    private String getMethodName(){
        return Thread.currentThread().getStackTrace()[3].getMethodName();
    }

    private String getClassName(){
        return Thread.currentThread().getStackTrace()[3].getClassName();
    }
}
