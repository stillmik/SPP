import math.MathOperations;
import timeTracer.TimeTracer;
import timeTracer.TraceResult;

public class Main {

    public static void main(String[] args) {
        TimeTracer timeTracer = new TimeTracer();
        MathOperations mathOperations = new MathOperations(timeTracer);
       /* mathOperations.largestTree();
        mathOperations.mediumTree();*/
        mathOperations.smallTree();
        TraceResult traceResult = timeTracer.getTraceResult();
        traceResult.getXML();
    }
}
