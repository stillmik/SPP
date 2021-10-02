package timeTracer;

public interface Tracer {

    void startTrace();

    void stopTrace();

    TraceResult getTraceResult();
}
