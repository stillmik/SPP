package timeTracer;

import tree.Tree;

import java.util.Stack;

public class TimeTracer implements Tracer {

    private Stack<Long> startTimeStack = new Stack<>();
    private Stack<String> methodInfStack = new Stack<>();
    private TraceResult traceResult = new TraceResult();

    private String methodClassName;
    private String methodName;

    private long processingTime;

    @Override
    public void startTrace() {
        processingTime = 0;
        methodInfStack.push(getMethodName() + "|" + getClassName());
        startTimeStack.push(System.currentTimeMillis());
    }

    @Override
    public void stopTrace() {
        getMethodInform();
        processingTime = System.currentTimeMillis() - startTimeStack.pop();
        traceResult.addToResult(methodName, processingTime, methodClassName);
    }

    @Override
    public TraceResult getTraceResult() {
        return traceResult;
    }

    private void getMethodInform() {
        String inf = methodInfStack.pop();
        methodClassName = inf.substring(inf.indexOf('|') + 1);
        methodName = inf.substring(0, inf.indexOf("|"));
    }

    private String getMethodName() {
        return Thread.currentThread().getStackTrace()[3].getMethodName();
    }

    private String getClassName() {
        return Thread.currentThread().getStackTrace()[3].getClassName();
    }
}
