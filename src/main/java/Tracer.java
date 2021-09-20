

public interface Tracer {

    void startTrace();

    void stpTrace();

    TraceResult getTraceResult();
}
