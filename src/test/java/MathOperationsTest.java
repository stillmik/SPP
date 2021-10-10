import math.MathOperations;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static timeTracer.TreeTraverser.getTypeOfNode;

import timeTracer.TimeTracer;
import timeTracer.TraceResult;
import timeTracer.TreeTraverser;
import timeTracer.Type;
import tree.Node;
import tree.Tree;

class MathOperationsTest {

    private static String timeTracerThreadName = "~java.lang.Thread.run()";

    @Test
    void makeSmallTree() {
        TimeTracer timeTracer = new TimeTracer();
        MathOperations mathOperations = new MathOperations(timeTracer);
        mathOperations.smallTree();
        TraceResult traceResult = timeTracer.getTraceResult();
        traceResult.getXMLandJSON();
        traceResult.getXML();
        traceResult.getJSON();
        traceResult.getXMLandJSONinConsole();
    }

    @Test
    void makeMediumTree() {
        TimeTracer timeTracer = new TimeTracer();
        MathOperations mathOperations = new MathOperations(timeTracer);
        mathOperations.mediumTree();
        TraceResult traceResult = timeTracer.getTraceResult();
        traceResult.getXMLandJSON();
        traceResult.getXML();
        traceResult.getJSON();
        traceResult.getXMLandJSONinConsole();
    }

    @Test
    void makeLargeTree() {
        TimeTracer timeTracer = new TimeTracer();
        MathOperations mathOperations = new MathOperations(timeTracer);
        mathOperations.largeTree();
        TraceResult traceResult = timeTracer.getTraceResult();
        traceResult.getXMLandJSON();
        traceResult.getXML();
        traceResult.getJSON();
        traceResult.getXMLandJSONinConsole();
    }

    @Test
    void countLargeTreeMethods() {
        TimeTracer timeTracer = new TimeTracer();
        Quant methodQuant = new Quant();
        MathOperations mathOperations = new MathOperations(timeTracer);
        int expected = 28;

        mathOperations.largeTree();
        TraceResult traceResult = timeTracer.getTraceResult();
        Tree tree = traceResult.getTree();
        Node current = tree.getRoot();
        traverseTree(current, methodQuant, tree.timeTracerName);
        assertEquals(expected, methodQuant.quantOfMethods);
    }

    @Test
    void largeTreeTimeMethodsAreValid() {
        TimeTracer timeTracer = new TimeTracer();
        MathOperations mathOperations = new MathOperations(timeTracer);

        mathOperations.largeTree();
        TraceResult traceResult = timeTracer.getTraceResult();
        Tree tree = traceResult.getTree();
        Node current = tree.getRoot();

    }

    @Test
    void countLargeTreeThreads() {
        TimeTracer timeTracer = new TimeTracer();
        Quant quant = new Quant();
        MathOperations mathOperations = new MathOperations(timeTracer);
        int expected = 4;

        mathOperations.largeTree();
        TraceResult traceResult = timeTracer.getTraceResult();
        Tree tree = traceResult.getTree();
        Node current = tree.getRoot();
        traverseTree(current, quant, tree.timeTracerName);
        assertEquals(expected, quant.quantOfThreads);
    }

    @Test
    void countLargeTreeThreadsTime() {
        TimeTracer timeTracer = new TimeTracer();
        Quant quant = new Quant();
        MathOperations mathOperations = new MathOperations(timeTracer);
        mathOperations.largeTree();
        TraceResult traceResult = timeTracer.getTraceResult();
        Tree tree = traceResult.getTree();
        TreeTraverser.makeTraceResultString(tree);
        traverseTree(tree.getRoot(), quant, tree.timeTracerName);
        long expected = quant.quantTimeOfThreads;
        assertEquals(expected, quant.quantTimeOfMethods);
    }

    private static void traverseTree(Node current, Quant quant, String timeTracerName) {
        if (current.address.contains(timeTracerName) || current.address.contains(timeTracerThreadName)) {
            quant.quantOfMethods++;
            quant.quantTimeOfMethods = quant.quantTimeOfMethods + current.time;
        }
        Type type = getTypeOfNode(current);
        if (type.equals(Type.THREAD)) {
            quant.quantOfThreads++;
            quant.quantTimeOfThreads = quant.quantTimeOfThreads + current.time;
            System.out.println();
        }
        for (int i = 0; i < current.children.size(); i++) {
            traverseTree(current.children.get(i), quant, timeTracerName);
        }
    }
}
