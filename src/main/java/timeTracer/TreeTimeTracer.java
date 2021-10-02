package timeTracer;

import tree.Tree;

import java.util.Stack;

public class TreeTimeTracer implements Tracer {

    private Tree tree;
    private TraceResult traceResult;

    private String methodClassName;
    private String methodName;

    private long processingTime;
    private long startTime;

    @Override
    public void startTrace() {
        processingTime = 0;
        startTime = System.currentTimeMillis();
        System.out.println(getClassName()+ "|" +getMethodName());
    }

    @Override
    public void stopTrace() {
        processingTime = System.currentTimeMillis() - startTime;
    }

    @Override
    public TraceResult getTraceResult() {
        return traceResult;
    }

    private String getMethodName() {
        return Thread.currentThread().getStackTrace()[3].getMethodName();
    }

    private String getClassName() {
        return Thread.currentThread().getStackTrace()[3].getClassName();
    }

    private String getMethodAddress(){
        return "";
    }
}
