package timeTracer;
import tree.Node;
import tree.Tree;

import java.util.ArrayList;

public class TimeTracer implements Tracer {

    private static Tree tree = new Tree();
    private TraceResult traceResult = new TraceResult(tree);

    @Override
    public void startTrace() {
        long startTime = System.currentTimeMillis();
        Node methodNode = getMethodNode();
        methodNode.time = startTime;
        tree.appendChild(tree.getRoot(), methodNode);
    }

    private String getMethodsAddress() {
        StringBuilder address = new StringBuilder();
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        for (int i = 4; i < elements.length; i++) {
            StackTraceElement element = elements[i];
            address.append("~").append(element.getClassName()).append(".").append(element.getMethodName()).append("()");
        }
        address.append("~").append(Thread.currentThread().getName());
        return address.toString();
    }

    private Node getMethodNode() {
        Node methodNode = new Node();
        methodNode.address = getMethodsAddress();
        StackTraceElement stackTraceElement= Thread.currentThread().getStackTrace()[3];
        StringBuilder element = new StringBuilder();
        methodNode.name = String.valueOf(element.append("~").append(stackTraceElement.getClassName()).append(".").append(stackTraceElement.getMethodName()).append("()"));
        methodNode.children = new ArrayList<>();
        return methodNode;
    }

    @Override
    public void stopTrace() {
        long stopTime = System.currentTimeMillis();
        Node pathNode = getMethodNode();
        pathNode.time = stopTime;
        tree.setTime(tree.getRoot(),pathNode);
    }

    @Override
    public TraceResult getTraceResult() {
        return traceResult;
    }
}
