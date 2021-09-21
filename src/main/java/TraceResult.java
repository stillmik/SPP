
class TraceResult {

    private String traceResult;

    TraceResult(String methodName,long traceResult,String className){
        this.traceResult="<method name=\""+methodName+"\" time=\""+traceResult+"\"ms. class=\""+className+"\">";
    }

    String getTraceResult(){
        return  traceResult;
    }
}
