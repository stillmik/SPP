package timeTracer;
import tree.Node;
import tree.Tree;

import java.util.ArrayList;

public class TimeTracer implements Tracer {

    private Tree tree = new Tree();
    private TraceResult traceResult;

    private String methodClassName;
    private String methodName;

    private long processingTime;
    private long startTime;

    @Override
    public void startTrace() {
        tree.appendChild(tree.getRoot(), getMethodNode());
        processingTime = 0;
        startTime = System.currentTimeMillis();
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

    public void print() {
        Node root = tree.getRoot();
        printing(root);
    }

    private void printing(Node node){
        for(int i=0;i<node.children.size();i++){
            node.printChildren();
            printing(node.children.get(i));
        }
    }

    @Override
    public void stopTrace() {
        processingTime = System.currentTimeMillis() - startTime;
    }

    @Override
    public TraceResult getTraceResult() {
        return traceResult;
    }
}
